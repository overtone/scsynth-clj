# scsynth-clj

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
