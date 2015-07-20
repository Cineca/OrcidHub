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
/*global module */
module.exports = function( grunt ) {
    'use strict';

    grunt.initConfig({
        meta: {
          version: '2.8.3',
          banner: '/*!\n' +
            ' * Modernizr v<%= meta.version %>\n' +
            ' * www.modernizr.com\n *\n' +
            ' * Copyright (c) Faruk Ates, Paul Irish, Alex Sexton\n' +
            ' * Available under the BSD and MIT licenses: www.modernizr.com/license/\n */'
        },
        qunit: {
            files: ['test/index.html']
        },
        lint: {
            files: [
                'grunt.js',
                'modernizr.js',
                'feature-detects/*.js'
            ]
        },
        min: {
            dist: {
                src: [
                    '<banner:meta.banner>',
                    'modernizr.js'
                ],
                dest: 'modernizr.min.js'
            }
        },
        watch: {
            files: '<config:lint.files>',
            tasks: 'lint'
        },
        jshint: {
            options: {
                boss: true,
                browser: true,
                curly: false,
                devel: true,
                eqeqeq: false,
                eqnull: true,
                expr: true,
                evil: true,
                immed: false,
                laxcomma: true,
                newcap: false,
                noarg: true,
                smarttabs: true,
                sub: true,
                undef: true
            },
            globals: {
                Modernizr: true,
                DocumentTouch: true,
                TEST: true,
                SVGFEColorMatrixElement : true,
                Blob: true
            }
        }
    });

    grunt.registerTask('default', 'min');

    // Travis CI task.
    grunt.registerTask('travis', 'qunit');
};
