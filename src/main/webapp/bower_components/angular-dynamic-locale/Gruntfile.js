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
(function () {
  'use strict';

  var BANNER =
    '/**\n' +
    ' * Angular Dynamic Locale - <%= pkg.version %>\n' +
    ' * https://github.com/lgalfaso/angular-dynamic-locale\n' +
    ' * License: MIT\n' +
    ' */\n';

  module.exports = function(grunt) {
    //grunt plugins
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-jscs');
    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-bump');
    grunt.loadNpmTasks('grunt-npm');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-concat');

    grunt.initConfig({
      pkg: grunt.file.readJSON('package.json'),
      jshint: {
        all: ['Gruntfile.js', 'src/*.js', 'test/*.js']
      },
      jscs: {
        src: ['src/**/*.js', 'test/**/*.js'],
        options: {
          config: ".jscs.json"
        }
      },
      karma: {
        unit: { configFile: 'karma.conf.js' },
        'unit.min': {
          configFile: 'karma.min.conf.js'
        },
        autotest: {
          configFile: 'karma.conf.js',
          autoWatch: true,
          singleRun: false
        },
        travis: {
          configFile: 'karma.conf.js',
          reporters: 'dots',
          browsers: ['PhantomJS']
        }
      },
      concat: {
        options: {
          banner: BANNER,
        },
        dist: {
          src: ['src/tmhDynamicLocale.js'],
          dest: 'dist/tmhDynamicLocale.js',
        },
      },
      uglify: {
        all: {
          files: {
            'tmhDynamicLocale.min.js': ['src/*.js']
          },
          options: {
            banner: BANNER,
            sourceMap: true
          }
        }
      },
      bump: {
        options: {
          files: ['package.json', 'bower.json'],
          commitFiles: ['package.json', 'bower.json'],
          tagName: '%VERSION%'
        }
      },
      'npm-publish': {
         options: {
           requires: ['jshint', 'karma:unit'],
           abortIfDirty: true
         }
      }
    });
    grunt.registerTask('release', ['jshint', 'jscs', 'karma:unit', 'uglify:all', 'karma:unit.min', 'publish']);
  };
}());

