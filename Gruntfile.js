/* jshint node: true */
// ^ we need some node modules like path, require

var path = require('path');
var parser = require('java-class-parser');
var _config = require('./bower.json');

module.exports = function(grunt) {
    var outputFileCss = grunt.option('outputFileCss') || 'target/applications/dist/scripts.js';
    var outputFileJs = grunt.option('outputFileJs') || 'target/applications/dist/base-war.css';

    // Time how long tasks take. Can help when optimizing build times
    require('time-grunt')(grunt);

    // Configurable paths for the application
    var appConfig = {
        app: '.',
        dist: 'html/dist',
        hostname: '0.0.0.0',
        port: 9001,
        url: function(file) {
            file = file || 'index.html';
            return 'http://' + this.hostname + ':' + this.port + '/' + file;
        },
        livereload: 35728,
        verbose: true,
        cwd: path.resolve()
    };

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        appConfig: appConfig,
        run_java: {
            javac: {
                command: "javac",
                javaOptions: {
                    "d": _config.java.dest
                }
            },
            "java": {
                command: "java",
                javaOptions: {
                    classpath: [_config.java.dest]
                }
            }
        },
        // https://www.npmjs.com/package/grunt-bower-task
        bower: {
            install: {
                options: {
                    verbose: appConfig.verbose,
                    layout: 'byComponent',
                    targetDir: appConfig.dist,
                    cleanTargetDir: true,
                    cleanBowerDir: false
                }
            }
        },
        clean: {
            dist: {
                src: [appConfig.dist]
            }
        },
        less: {
            project: {
                options: {
                    sourceMap: true,
                    dumpLineNumbers: 'comments',
                    relativeUrls: true
                },
                files: {
                    'angular/css/bootstrap-responsive.css': 'angular/less/style.less'
                }
            }
        },
        concat: {
            options: {
                stripBanners: true
            },
            css: {
                src: ['target/concat/**/*.css'],
                dest: outputFileCss
            },
            js: {
                options: {
                    separator: ';'
                },
                src: ['target/concat/**/*.js'],
                dest: outputFileJs
            }
        },
        uglify: {
            options: {
                banner: '<%= banner %>'
            },
            dist: {
                src: '<%= concat.dist.dest %>',
                dest: 'target-grunt/<%= pkg.name %>.min.js'
            }
        },
        jshint: {
            options: {
                curly: true,
                eqeqeq: true,
                immed: true,
                latedef: true,
                newcap: true,
                noarg: true,
                sub: true,
                undef: true,
                unused: true,
                boss: true,
                eqnull: true,
                browser: true,
                globals: {
                    jQuery: true
                }
            },
            gruntfile: {
                src: 'Gruntfile.js'
            },
            lib_test: {
                src: ['test/**/*.js']
            }
        },
        qunit: {
            files: ['test/**/*.html']
        },
        watch: {
            gruntfile: {
                files: '<%= jshint.gruntfile.src %>',
                tasks: ['jshint:gruntfile']
            },
            lib_test: {
                files: '<%= jshint.lib_test.src %>',
                tasks: ['jshint:lib_test', 'qunit']
            },
            livereload: {
                options: {
                    livereload: '<%= connect.options.livereload %>'
                },
                files: [
                    "angular/**/*.html",
                    "angular/**/*.js",
                    "angular/**/*.css",
                    "html/*.html"
                ]
            }
        },

        // Run some tasks in parallel to speed up the build process
        concurrent: {
            server: {

            }
        },

        // The actual grunt server settings
        connect: {
            options: {
                port: '<%= appConfig.port %>',
                // Change this to '0.0.0.0' to access the server from outside.
                hostname: '<%= appConfig.hostname %>',
                livereload: '<%= appConfig.livereload %>',
                base: '<%= appConfig.app %>'
            },
            proxies: [{
                context: '/apps', // When the url contains this...
                host: 'madhu.noip.me', // Proxy to this host
                port: 8080,
                https: false,
                changeOrigin: true,
                xforward: false,
                headers: {
                    "x-custom-added-header": "value"
                }
            }, {
                context: '/services/api/', // When the url contains this...
                host: 'madhu.noip.me', // Proxy to this host
                port: 8080,
                https: false,
                changeOrigin: true,
                xforward: false,
                headers: {
                    "x-custom-added-header": "value"
                }
            }],
            livereload: {
                options: {
                    open: {
                        target: appConfig.url()
                    },
                    middleware: function(connect, options) {
                        if (!Array.isArray(options.base)) {
                            options.base = [options.base];
                        }

                        var middlewares = [];

                        var fnLog = function(rq, rs, next) {
                            grunt.log.debug('hioo ' + rq.url);
                            return next();
                        }

                        // Setup the proxy
                        middlewares.push(require("grunt-connect-proxy/lib/utils").proxyRequest);
                        middlewares.push(fnLog);

                        // Serve static files.
                        options.base.forEach(function(base) {
                            middlewares.push(connect.static(base));
                        });
                        /* */
                        middlewares.push(connect.static('.tmp'));
                        middlewares.push(connect().use(
                            '/vendor',
                            connect.static('./vendor')
                        ));
                        middlewares.push(connect.static(appConfig.app));

                        return middlewares;
                    }
                }
            },
            test: {
                options: {
                    port: 7001,
                    middleware: function(connect) {
                        return [
                            connect.static('.tmp'),
                            connect.static('test'),
                            connect().use(
                                '/vendor',
                                connect.static('./vendor')
                            ),
                            connect.static(appConfig.app)
                        ];
                    }
                }
            },
            dist: {
                options: {
                    open: true,
                    base: '<%= yeoman.dist %>'
                }
            }
        }
    });

    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);
    require('load-grunt-tasks')(grunt, {
        pattern: ['grunt-contrib-*', 'grunt-connect-*']
    });


    /*
     Tasks added later.
     */
    grunt.loadNpmTasks('grunt-concurrent');
    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-run-java');

    // Run npm install for angular ui
    grunt.registerTask('uiboot-npm', function(name) {
        var cwd = './' + appConfig.dist + '/angular-ui-bootstrap/';
        var exec = require('child_process').exec;
        var done = grunt.task.current.async(); // this.async();
        grunt.log.writeln('Please wait while we install local npm dependencies for angular-ui ...');
        // http://stackoverflow.com/questions/13957303/nodejs-grunt-child-process-callback-function-example
        exec('npm install', {
            cwd: cwd
        }, function(err, std, stderr) {
            grunt.log.writeln(std);
            grunt.log.writeln(stderr);
            done(err);
        });
    });

    grunt.registerTask('uiboot-grunt', function(name) {
        var cwd = './' + appConfig.dist + '/angular-ui-bootstrap/';
        var uiBootGrunt = require(cwd + 'Gruntfile.js')(grunt);
        uiBootGrunt.file.setBase(cwd);
        require('load-grunt-tasks')(uiBootGrunt);
        uiBootGrunt.task.run(name);
    });

    grunt.registerTask('noop', '', function() {
        var concat = grunt.config.get('concat');
        grunt.log.writeln(JSON.stringify(arguments));
        grunt.log.writeln(JSON.stringify(concat));
    });

    grunt.registerTask('java', 'Compile and Execute Java files.', function(name) {
        var srcFiles = [],
            classFiles = [];
        // search for matching java file in source path.
        grunt.file.recurse(_config.java.src, function(path, root, dir, file) {
            var fType = file.split('.').pop();
            var fName = /^[^.]+/.exec(file);
            if (fName == name) {
                srcFiles.push(path);
                classFiles.push(dir.replace('/', '.') + '.' + fName)
            }
        });
        grunt.config.set('run_java.javac.sourceFiles', srcFiles);
        grunt.config.set('run_java.java.className', classFiles);
        grunt.task.run('run_java:javac', 'run_java:java');
    });

    grunt.registerTask('default', ['clean', 'bower:install', 'uiboot-npm', 'uiboot-grunt:default']);

    grunt.registerTask('printConfig', function() {
        grunt.log.writeln(JSON.stringify(grunt.config(), null, 2));
    });

    grunt.registerTask('serve', 'Compile then start a connect web server', function(htmlFile) {
        if (htmlFile) {
            grunt.config.set('connect.livereload.options.open.target', appConfig.url(htmlFile));
        }
        grunt.task.run([
            'concurrent:server',
            'configureProxies:server',
            'connect:livereload',
            'watch'
        ]);
    });

};