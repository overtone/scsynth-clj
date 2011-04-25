(ns scsynth.core
  (:use [clj-native.direct :only [defclib loadlib typeof]]
        [clj-native.structs :only [byref byval]]
        [clj-native.callbacks :only [callback]]))
(defclib
  scsynth
  (:libname "scsynth")
  (:functions))
