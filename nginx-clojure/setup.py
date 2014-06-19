# nginx-clojure setup


import subprocess
import sys
import setup_util
import os

def start(args, logfile, errfile):
  #  setup_util.replace_text('dart-stream/postgresql.yaml', 'host: .*', 'host: ' + args.database_host)
  #  setup_util.replace_text('dart-stream/mongodb.yaml', 'host: .*', 'host: ' + args.database_host)
  try:
    #
    # create nginx configuration
    #
    conf = []
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

    test_dir = 'nginx-clojure'

    nginx_dir = test_dir+'/'+'nginx-clojure-0.2.2/nginx-1.6.0'

    subprocess.Popen('nginx-linux-x64', shell=True,
                     cwd=nginx_dir, stderr=errfile, stdout=logfile);

    return 0
  except subprocess.CalledProcessError:
    return 1

def stop(logfile, errfile):
  #
  # stop nginx
  #
  try:
    # listen on 8080
    subprocess.check_call("lsof -t -sTCP:LISTEN -i:8080 | xargs kill", shell=True, stderr=errfile, stdout=logfile)
    return 0
  except subprocess.CalledProcessError:
    return 1

