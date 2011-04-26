# scsynth-clj

    git clone --recursive git://github.com/overtone/scsynth-clj.git
    git submodule foreach git pull

## Compiling on Ubuntu Maverick Meerkat

    sudo apt-get install cmake build-essential libjack-dev libsndfile1-dev libreadline-dev libfftw3-dev libicu-dev

    cd supercollider
    git am ../0001-Added-scsynth-interop.cpp-and-scsynth-interop.h.patch
    cmake -DSC_QT=OFF -DSC_EL=OFF .
    make
    cp server/scsynth/libscsynth.so ../native/linux/x86_64
    cp server/plugins/*.so ../native/linux/x86_64/ugens

    cd ../sc3-plugins
    cmake -DSC_PATH=$PWD/../supercollider/ .
    make
    cp source/*.so ../native/linux/x86_64/ugens

## Using Jack with Pulseaudio on Ubuntu Maverick Meerkat

    sudo apt-get install pulseaudio-module-jack jackd1

    cat > ~/.jackdrc <<EOF
    /usr/bin/jackd -r -ddummy -r48000 -p1024
    EOF
    pacmd load-module module-jack-source channels=2 connect=false;
    pacmd load-module module-loopback source=jack_in;

## Compiling on Mac OS X Snow Leopard

Dependencies

 * Homebrew
 * Git
 * Cmake
 * Xcode



## License

Copyright (C) 2011 Fabian Aussems

Distributed under the Eclipse Public License, the same as Clojure.
