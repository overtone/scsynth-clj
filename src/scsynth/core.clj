(ns scsynth.core
  (:use [clj-native.direct :only [defclib loadlib typeof]]
        [clj-native.structs :only [byref byval]]
        [clj-native.callbacks :only [callback]])
  (:import [com.sun.jna Pointer]
           [java.nio ByteBuffer ByteOrder]))

(defclib
  scsynth
  (:libname "scsynth")
  (:structs
   (sndbuf :sampleRate double
           :sampleDur double
           :data void*
           :channels int
           :samples int
           :frames int
           :mask int
           :mask1 int
           :coord int
           :sndfile void*)
   (start-options
     :verbosity int
     :UGensPluginPath constchar*
     :inDeviceName constchar*
     :outDeviceName constchar*
     :numControlBusChannels int
     :numAudioBusChannels int
     :numInputBusChannels int
     :numOutputBusChannels int
     :bufLength int
     :preferredHardwareBufferFrameSize int
     :preferredSampleRate int
     :numBuffers int
     :maxNodes int
     :maxGraphDefs int
     :realTimeMemorySize int
     :maxWireBufs int
     :numRGens int))
  (:callbacks
   (loaded-cb [constchar*] void)
   (crashed-cb [] void)
   (reply-cb [void* void* int] void))
  (:functions
   (scsynth_interop_get_default_start_options [] start-options*)
   (scsynth_interop_get_device_count [] int)
   (scsynth_interop_get_device_name [int] constchar*)
   (scsynth_interop_get_device_max_input_channels [int] int)
   (scsynth_interop_get_device_max_output_channels [int] int)

   (scsynth_interop_init [] int)
   (scsynth_interop_start [start-options*] void*)
   (scsynth_interop_cleanup [] void)
   (scsynth_interop_copy_sndbuf [void* int] void*)

   (scsynth_interop_wait_for_quit [void*] void)
   (scsynth_interop_open_udp [void* int] int)
   (scsynth_interop_open_tcp [void* int int int] int)
   (scsynth_interop_send_packet [void* int char* reply-cb] bool)

   (scsynth_interop_set_crashed_callback [crashed-cb] void)
   (scsynth_interop_crash [] void)

   (scsynth_interop_load_plugin [constchar*] void)
   (scsynth_interop_set_plugin_loaded_callback [loaded-cb] void)))

(defn start [plugins-path]
  (System/setProperty "jna.library.path" (System/getProperty "java.library.path"))
  (loadlib scsynth)
  (scsynth_interop_init)
  (let [opts (scsynth_interop_get_default_start_options)]
    (set! (.UGensPluginPath opts) plugins-path)
    (scsynth_interop_start opts)))

(defn make-reply-callback [reply-fn]
  (callback reply-cb (fn [addr buf size]
                       (let [bb (.order (.getByteBuffer buf 0 size) ByteOrder/BIG_ENDIAN)]
                         (reply-fn bb)
                         ))))

(defn scsynth-send-packet [world reply-callback bb]
  (scsynth_interop_send_packet world (.limit bb) bb reply-callback))

(comment
  (use 'osc)

  (def *world* (start "native/linux/x86_64/ugens"))

  (def *global-reply-cb* (make-reply-callback (fn [bb] (let [msg (osc-decode-packet bb)]
                                                        (println msg)))))

  (def *peer* (assoc (osc-peer) :send-fn (fn [peerobj buf] (scsynth-send-packet *world* *global-reply-cb* buf))))

  (defn snd [path & args]
    (apply osc-send *peer* path args))

  (snd "/status"))

;(scsynth_interop_wait_for_quit *world)
;(scsynth_interop_cleanup)
