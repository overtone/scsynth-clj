# scsynth-clj

## Using Jack with Pulseaudio on Ubuntu Maverick Meerkat

dependencies

    sudo apt-get install pulseaudio-module-jack jackd2

    cat > ~/.jackdrc <<EOF
    /usr/bin/jackd -ddummy -r48000 -p1024
    EOF
    
    sudo su -c 'echo load-module module-jack-source channels=2 connect=false >> /etc/pulse/default.pa'
    sudo su -c 'echo load-module module-loopback source=jack_in >> /etc/pulse/default.pa'
    
realtime

    sudo su -c 'echo @audio - rtprio 99 >> /etc/security/limits.conf'
    sudo su -c 'echo @audio - memlock 250000 >> /etc/security/limits.conf'
    sudo su -c 'echo @audio - nice -10 >> /etc/security/limits.conf'
    sudo su -c 'echo realtime-scheduling = yes >> /etc/pulse/daemon.conf'

no realtime

    sudo su -c 'echo realtime-scheduling = no >> /etc/pulse/daemon.conf'

After starting overtone

    jack_connect SuperCollider:out_1 "PulseAudio JACK Source:front-left"
    jack_connect SuperCollider:out_2 "PulseAudio JACK Source:front-right"


## License

Copyright (C) 2011 Fabian Aussems

Distributed under the Eclipse Public License, the same as Clojure.
