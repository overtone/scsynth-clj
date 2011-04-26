(ns scsynth.core
  (:use [clj-native.direct :only [defclib loadlib typeof]]
        [clj-native.structs :only [byref byval]]
        [clj-native.callbacks :only [callback]]))

(defclib
  scsynth
  (:libname "scsynth")
  (:functions))

;;
;struct ScsynthInteropStartOptions
;{
;  int verbosity;
;  const char* UGensPluginPath;
;  const char* inDeviceName;
;  const char* outDeviceName;
;  int numControlBusChannels;
;  int numAudioBusChannels;
;  int numInputBusChannels;
;  int numOutputBusChannels;
;  int bufLength;
;  int preferredHardwareBufferFrameSize;
;  int preferredSampleRate;
;  int numBuffers;
;  int maxNodes;
;  int maxGraphDefs;
;  int realTimeMemorySize;
;  int maxWireBufs;
;  int numRGens;
; };
;
;   struct ScsynthInteropStartOptions* scsynth_interop_get_default_start_options();
;   int scsynth_interop_get_device_count();
;   const char* scsynth_interop_get_device_name(int i);
;   int scsynth_interop_get_device_max_input_channels(int i);
;   int scsynth_interop_get_device_max_output_channels(int i);
;
;   int scsynth_interop_init();
;   void* scsynth_interop_start(struct ScsynthInteropStartOptions *options); // returns struct World*
;   void scsynth_interop_cleanup();
;   void* scsynth_interop_copy_sndbuf(void* world, unsigned index);  // world is a struct World* , returns struct SndBuf*

;   void scsynth_interop_wait_for_quit(void* world); // world is a struct World*
;   int scsynth_interop_open_udp(void* world, int inPort); // world is a struct World*
;   int scsynth_interop_open_tcp(void* world, int inPort, int inMaxConnections, int inBacklog); // world is a struct World*
;   bool scsynth_interop_send_packet(void* world, int inSize, char *inData, void (*infunc)(void*, void*,int)); // world is a struct World*, infunc is a ReplyFunc inFunc,

;   void scsynth_interop_set_crashed_callback( void (*ptr)(void)  );
;   void scsynth_interop_crash();
;
;   void scsynth_interop_load_plugin(const char *filename);
;   void scsynth_interop_set_plugin_loaded_callback( void (*ptr)(const char*)  );


