# scsynth-clj

    git clone --recursive git://github.com/overtone/scsynth-clj.git

## Compiling on Ubuntu Maverick Meerkat

Dependencies

    sudo apt-get install cmake build-essential libjack-dev libsndfile1-dev libreadline-dev libfftw3-dev libicu-dev

    cd supercollider
    git am ../0001-Added-scsynth-interop.cpp-and-scsynth-interop.h.patch
    cmake -DSC_QT=OFF -DSC_EL=OFF .
    make
    cp server/scsynth/libscsynth.so ../native/linux/x86_64
    cp server/plugins/*.so ../native/linux/x86_64/ugens

## Compiling on Mac OS X Snow Leopard

Dependencies

 * Homebrew
 * Git
 * Cmake

## License

Copyright (C) 2011 Fabian Aussems

Distributed under the Eclipse Public License, the same as Clojure.
