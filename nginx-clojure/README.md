# hacked version of nginx-clojure test suite


-- uses fully compiled binaries for nginx-clojure since this seems like a pain otherwise







# TODOs



## dependencies


### libjpeg

    ./nginx-linux-x64: error while loading shared libraries: libjpeg.so.62: cannot open shared object file: No such file or directory


    sudo apt-get install libjpeg62



## boot


    nginx: [emerg] mkdir() "/home/tfb/FrameworkBenchmarks/nginx-clojure/nginx-clojure-0.2.2/nginx-1.6.0/temp/client_temp" failed (2: No such file or directory)


    mkdir temp
    mkdir client_temp



    2014/06/18 23:35:50 [error] 14734#0: can not initialize jvm for load dynamic lib, maybe wrong jvm_path!


this system doesnt have oracle's jdk, so use

    jvm_path "/usr/lib/jvm/java-7-openjdk-amd64/jre/lib/amd64/server/libjvm.so";

in the `nginx.conf`



## test

- disable logs
