# scsynth-clj

    git clone --recursive git://github.com/overtone/scsynth-clj.git
    git submodule foreach git pull

## Compiling SuperCollider native libraries 

Ubuntu dependencies:

    sudo apt-get install cmake build-essential libjack-dev libsndfile1-dev libreadline-dev libfftw3-dev libicu-dev maven2

OSX dependencies:

 * Homebrew
 * Git
 * Cmake
 * Xcode

In the commands below replace {platform} with one of <linux, macosx, windows> and replace {arch} with one of <x86, x86\_64> so they go in one of the existing directories inside native.

#### Compile SuperCollider

    cd supercollider
    git checkout -b scsynth-clj
    git am ../0001-Added-scsynth-interop.cpp-and-scsynth-interop.h.patch
    mkdir scsynth_clj_build
    cd scsynth_clj_build
    cmake -DSC_QT=OFF -DSC_EL=OFF -DSUPERNOVA=off ..
    make
    cp server/scsynth/libscsynth.so ../../native/{platform}/{arch}
    cp server/plugins/*.so ../../native/{platform}/{arch}/ugens

#### Compile the SC3 plugins

    cd ../sc3-plugins
    mkdir scsynth_clj_build
    cd scsynth_clj_build
    cmake -DSC_PATH=$PWD/../supercollider/ -DSUPERNOVA=off ..
    make
    cp source/*.so ../../native/{platform}/{arch}/ugens

#### Install

Create a jar file containing the native libs and install it in your local maven repository.

    ./install-natives

## Using Jack with Pulseaudio on Ubuntu Maverick Meerkat

    sudo apt-get install pulseaudio-module-jack jackd2

    cat > ~/.jackdrc <<EOF
    /usr/bin/jackd -r -ddummy -r48000 -p1024
    EOF
    pacmd load-module module-jack-source channels=2 connect=false;
    pacmd load-module module-loopback source=jack_in;

After starting overtone

    jack_connect SuperCollider:out_1 "PulseAudio JACK Source:front-left"
    jack_connect SuperCollider:out_2 "PulseAudio JACK Source:front-right"

## License

Copyright (C) 2011 Fabian Aussems

Distributed under the Eclipse Public License, the same as Clojure.
