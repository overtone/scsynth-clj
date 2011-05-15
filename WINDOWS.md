Requirements:
Mingw32
CMake
set PATH=%PATH%;C:\mingw\bin

In SC_Lib_Cintf.cpp change 
HINSTANCE hinstance = LoadLibrary( filename );
to:
HINSTANCE hinstance = LoadLibraryEx( filename, NULL, LOAD_WITH_ALTERED_SEARCH_PATH );

cmake .. -DSC_PSYCOLLIDER=off -DSC_QT=OFF -DSC_EL=OFF -DSUPERNOVA=off -G "MinGW Makefiles" -DPORTAUDIO_LIBRARIES=c:\projects\overtone\scsynth-clj\deps\windows\x86\portaudio19\portaudio_x86.lib -DPORTAUDIO_INCLUDE_DIRS=c:\projects\overtone\scsynth-clj\deps\windows\x86\portaudio19 -DPTHREADS_INCLUDE_DIR=c:\projects\overtone\scsynth-clj\deps\windows\x86\pthreads-win32 -DPTHREADS_LIBRARY=c:\projects\overtone\scsynth-clj\deps\windows\x86\pthreads-win32\pthreadVCE2.lib -DSNDFILE_INCLUDE_DIR=c:\projects\overtone\scsynth-clj\deps\windows\x86\libsndfile\include -DSNDFILE_LIBRARY=c:\projects\overtone\scsynth-clj\deps\windows\x86\libsndfile\libsndfile-1.lib -DFFTW3F_INCLUDE_DIR=c:\projects\overtone\scsynth-clj\deps\windows\x86\fftw3 -DFFTW3F_LIBRARY=c:\projects\overtone\scsynth-clj\deps\windows\x86\fftw3\libfftw.lib

mingw32-make

copy scx and dll files as seen in native/windows/x86

== PLUGINS

cmake .. -G "MinGW Makefiles" -DSC_PATH=C:\projects\overtone\scsynth-clj\supercollider -DSUPERNOVA=off -DPORTAUDIO_LIBRARIES=c:\projects\overtone\scsynth-clj\deps\windows\x86\portaudio19\portaudio_x86.lib -DPORTAUDIO_INCLUDE_DIRS=c:\projects\overtone\scsynth-clj\deps\windows\x86\portaudio19 -DPTHREADS_INCLUDE_DIR=c:\projects\overtone\scsynth-clj\deps\windows\x86\pthreads-win32 -DPTHREADS_LIBRARY=c:\projects\overtone\scsynth-clj\deps\windows\x86\pthreads-win32\pthreadVCE2.lib -DSNDFILE_INCLUDE_DIR=c:\projects\overtone\scsynth-clj\deps\windows\x86\libsndfile\include -DSNDFILE_LIBRARY=c:\projects\overtone\scsynth-clj\deps\windows\x86\libsndfile\libsndfile-1.lib -DFFTW3F_INCLUDE_DIR=c:\projects\overtone\scsynth-clj\deps\windows\x86\fftw3 -DFFTW3F_LIBRARY=c:\projects\overtone\scsynth-clj\deps\windows\x86\fftw3\libfftw.lib

mingw32-make

diff --git a/source/CMakeLists.txt b/source/CMakeLists.txt
index bf3d46a..13e2faf 100644
--- a/source/CMakeLists.txt
+++ b/source/CMakeLists.txt
@@ -183,9 +183,9 @@ BUILD_PLUGIN(MembraneUGens "MembraneUGens/Membrane_shape.c;MembraneUGens/Membran
              "" "MembraneUGens")
 
 # LadspaUGen
-BUILD_PLUGIN(LadspaUGen "LadspaUGen/LadspaUGen.cpp;LadspaUGen/search.c" "" "LadspaUGen")
-add_executable(ladspalist LadspaUGen/ladspalist.c LadspaUGen/search.c)
-target_link_libraries(ladspalist dl)
+#BUILD_PLUGIN(LadspaUGen "LadspaUGen/LadspaUGen.cpp;LadspaUGen/search.c" "" "LadspaUGen")
+#add_executable(ladspalist LadspaUGen/ladspalist.c LadspaUGen/search.c)
+#target_link_libraries(ladspalist dl)
 
 # PitchDetectionUGens
 file(GLOB PitchDetectionSources PitchDetection/*cpp)
@@ -228,8 +228,8 @@ else()
     set (NCAnalysisLibs ${FFTW3F_LIBRARY})
 endif()
 
-BUILD_PLUGIN(NCAnalysisUGens "${NCAnalysisSources};${FFTSOURCES}"
-             ${NCAnalysisLibs} "NCAnalysisUGens")
+#BUILD_PLUGIN(NCAnalysisUGens "${NCAnalysisSources};${FFTSOURCES}"
+             #${NCAnalysisLibs} "NCAnalysisUGens")
 
 # AYUGens
 if (AY)
@@ -264,9 +264,9 @@ if (CMAKE_SYSTEM_NAME MATCHES "Linux")
             PERMISSIONS ${PERMISSIONS})
 endif()        
 
-install(TARGETS ladspalist
-        DESTINATION ${INSTALL_DESTINATION_LADSPALIST}
-        PERMISSIONS ${PERMISSIONS})
+#install(TARGETS ladspalist
+        #DESTINATION ${INSTALL_DESTINATION_LADSPALIST}
+        #PERMISSIONS ${PERMISSIONS})
 
 # install help and sc specific files that are found in 'PLUGIN_NAME/sc' folder
 foreach (DIR ${PLUGIN_DIRS};${PLUGIN_DIRS_EXTRA})


== WIN64 - NOT WORKING
Requirements:
Ming64
set PATH=%PATH%;C:\mingw\bin;C:\mingw64\bin

set CC=x86_64-w64-mingw32-gcc
set CXX=x86_64-w64-mingw32-g++
set LD=x86_64-w64-mingw32-ld
set AR=x86_64-w64-mingw32-ar
set NM=x86_64-w64-mingw32-nm
set OBJCOPY=x86_64-w64-mingw32-objcopy
set OBJDUMP=x86_64-w64-mingw32-objdump
set RANLIB=x86_64-w64-mingw32-ranlib
set RC_COMPILER=x86_64-w64-mingw32-windres
set STRIP=x86_64-w64-mingw32-strip

cmake .. -DSC_PSYCOLLIDER=off -DSC_QT=OFF -DSC_EL=OFF -DSUPERNOVA=off -G "MinGW Makefiles" -DPORTAUDIO_LIBRARIES=c:\projects\overtone\scsynth-clj\deps\windows\x86_64\portaudio19\portaudio_x64.lib -DPORTAUDIO_INCLUDE_DIRS=c:\projects\overtone\scsynth-clj\deps\windows\x86_64\portaudio19 -DPTHREADS_INCLUDE_DIR=c:\projects\overtone\scsynth-clj\deps\windows\x86_64\pthreads-win32 -DPTHREADS_LIBRARY=c:\projects\overtone\scsynth-clj\deps\windows\x86_64\pthreads-win32\pthreadVSE2.lib -DSNDFILE_INCLUDE_DIR=c:\projects\overtone\scsynth-clj\deps\windows\x86_64\libsndfile\include -DSNDFILE_LIBRARY=c:\projects\overtone\scsynth-clj\deps\windows\x86_64\libsndfile\libsndfile-1.lib -DFFTW3F_INCLUDE_DIR=c:\projects\overtone\scsynth-clj\deps\windows\x86_64\fftw3 -DFFTW3F_LIBRARY=c:\projects\overtone\scsynth-clj\deps\windows\x86_64\fftw3\libfftw.lib


mingw32-make