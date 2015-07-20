/*
 * This file is part of huborcid.
 *
 * huborcid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * huborcid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with huborcid.  If not, see <http://www.gnu.org/licenses/>.
 */
'use strict';

module.exports = function (grunt) {
  // load all grunt tasks
  require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);

  // configurable paths
  var yeomanConfig = {
    src: 'src',
    dist: 'dist',
    test: 'test',
    temp: '.temp'
  };

  try {
    yeomanConfig.src = require('./bower.json').appPath || yeomanConfig.src;
  } catch (e) {}

  grunt.initConfig({
    yeoman: yeomanConfig,
    pkg: grunt.file.readJSON('bower.json'),
    meta: {
      banner:
        '/**\n' +
        ' * <%= pkg.name %>\n' +
        ' * @version v<%= pkg.version %> - <%= grunt.template.today("yyyy-mm-dd") %>\n' +
        ' * @author <%= pkg.author.name %> (<%= pkg.author.email %>)\n' +
		    ' * @link <%= pkg.homepage %>\n' +
        ' * @license <%= _.map(pkg.licenses, function(l) { return l.type + "(" + l.url + ")"; }).join(", ") %>\n' +
        '**/\n\n'
    },
    jshint: {
      options: {
        jshintrc: '.jshintrc'
      },
      all: [
        'Gruntfile.js',
        '<%= yeoman.src %>/**/*.js'
      ],
      test: {
        src: ['<%= yeoman.test %>/spec/**/*.js'],
        options: {
          jshintrc: '<%= yeoman.test %>/.jshintrc'
        }
      }
    },
    karma: {
      options: {
        configFile: 'karma.conf.js'
      },
      unit: {
        options: {
          singleRun: false
        }
      },
      final: {
        options: {
          singleRun: true
        }
      },
      travis: {
        browsers: ['PhantomJS'],
        options: {
          singleRun: true
        }
      }
    },
    clean: {
      dist: {
        files: [{
          dot: true,
          src: [
            '<%= yeoman.dist %>/*',
            '!<%= yeoman.dist %>/.git*'
          ]
        }]
      },
      temp: {
        src: ['<%= yeoman.dist %>/<%= yeoman.temp %>']
      }
    },
    ngmin: {
      dist: {
        expand: true,
        cwd: '<%= yeoman.src %>',
        src: ['**/*.js'],
        dest: '<%= yeoman.dist %>/<%= yeoman.temp %>'
      }
    },
    concat: {
      options: {
        banner: '<%= meta.banner %>',
        process: function(src, filepath) {
          // don't strip 'use strict' in the prefix
          if (filepath === 'bsSwitch.prefix') {
            return src;
          }
          return '// Source: ' + filepath + '\n' +
            src.replace(/(^|\n)[ \t]*('use strict'|"use strict");?\s*/g, '$1');
        }
      },
      dist: {
        src: ['bsSwitch.prefix', 'common/*.js', '<%= yeoman.dist %>/<%= yeoman.temp %>/**/*.js', 'bsSwitch.suffix'],
        dest: '<%= yeoman.dist %>/<%= pkg.name %>.js'
      }
    },
    uglify: {
      options: {
        banner: '<%= meta.banner %>'
      },
      min: {
        files: {
          '<%= yeoman.dist %>/<%= pkg.name %>.min.js': '<%= concat.dist.dest %>'
        }
      }
    }
  });

  // Test the directive
  grunt.registerTask('test', ['jshint', 'karma:unit']);
  grunt.registerTask('test-travis', ['jshint', 'karma:travis']);

  // Build the directive
  //  - clean, cleans the output directory
  //  - ngmin, prepares the angular files
  //  - concat, concatenates and adds a banner to the debug file
  //  - uglify, minifies and adds a banner to the minified file
  //  - clean:temp, cleans the ngmin-ified directory
  grunt.registerTask('build', ['clean', 'ngmin', 'concat', 'uglify', 'clean:temp']);

  // Default task, do everything
  grunt.registerTask('default', ['test-travis', 'build']);
};
