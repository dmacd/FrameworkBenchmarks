# nginx-clojure setup


import subprocess
import sys
import setup_util
import os

def start(args, logfile, errfile):


  stop(logfile, errfile); # kill anyone that gets in our way


  #  setup_util.replace_text('dart-stream/postgresql.yaml', 'host: .*', 'host: ' + args.database_host)
  #  setup_util.replace_text('dart-stream/mongodb.yaml', 'host: .*', 'host: ' + args.database_host)
  try:




    test_dir = 'nginx-clojure'

    handlers_dir = test_dir + '/' + 'handlers';

    nginx_dir = test_dir+'/'+'nginx-clojure-0.2.2/nginx-1.6.0'


    # create nginx configuration

    #conf = []
    #    conf.append('worker_processes ' + str(args.max_threads) + ';')
    #
    # write nginx configuration to disk
    #
    #with open('dart-stream/nginx.conf', 'w') as f:
    #  f.write('\n'.join(conf))
    #
    # start nginx
    #
    #subprocess.Popen('sudo /usr/local/nginx/sbin/nginx -c `pwd`/nginx.conf', shell=True, cwd='dart-stream', stderr=errfile, stdout=logfile);
    #subprocess.Popen('`pwd`/sudo /usr/local/nginx/sbin/nginx -c `pwd`/nginx.conf', shell=True, cwd='dart-stream', stderr=errfile, stdout=logfile);


    #setup_util.replace_text("nodejs/hello.js", "mongodb:\/\/.*\/hello_world", "mongodb://" + args.database_host + "/hello_world")


    # configure database host
    setup_util.replace_text(nginx_dir+"/"+"conf/nginx.conf", ":db-host \".*\"", args.database_host)



    # build the handlers project

#    subprocess.check_call("lein clean", shell=True, cwd=handlers_dir, stderr=errfile, stdout=logfile)
#    subprocess.check_call("lein deps", shell=True, cwd=handlers_dir, stderr=errfile, stdout=logfile)
#    subprocess.check_call("rm -rf target", shell=True, cwd=handlers_dir)

    # pack all dependencies into a single jar: target/handlers-standalone.jar
    subprocess.check_call("lein uberjar", shell=True, cwd=handlers_dir, stderr=errfile, stdout=logfile)

    # -server is much faster
    # 'lein run' passes '-client -XX:+TieredCompilation -XX:TieredStopAtLevel=1' which make it starts fast, but runs slow
    #command = "java -server -jar target/http-kit-standalone.jar --db-host " + args.database_host

    # todo: maybe copy output jar to jars dir in server? or better to avoid possibility of
    # failure and let it point to target jar directly




    subprocess.Popen('./nginx-linux-x64', shell=True,
                     cwd=nginx_dir, stderr=errfile, stdout=logfile);


#     subprocess.Popen('`pwd`/nginx-clojure-0.2.2/nginx-1.6.0/nginx-linux-x64', shell=True,
#                      cwd=n, stderr=errfile, stdout=logfile);

    return 0
  except subprocess.CalledProcessError:
    return 1

def stop(logfile, errfile):
  #
  # stop nginx
  #
  try:
    # listen on 8080
    subprocess.check_call("ps -A | grep nginx | xargs kill", shell=True, stderr=errfile, stdout=logfile) # hacky but should kill any lingering nginx
    subprocess.check_call("lsof -t -sTCP:LISTEN -i:8080 | xargs kill", shell=True, stderr=errfile, stdout=logfile)
    return 0
  except subprocess.CalledProcessError:
    return 1

