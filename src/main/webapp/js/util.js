/*
 *  Copyright (C) 2010 Red Hat, Inc. All rights reserved.
 *
 *  This is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU Lesser General Public License as
 *  published by the Free Software Foundation; either version 2.1 of
 *  the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this software; if not, write to the Free
 *  Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *  02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

/**
 * @author Nabil Benothman
 */

$j = jQuery.noConflict();

/**
 * Function used to add bubble info messages to input text
 */
function bubble() {
    
    $j(function () {
        $j('.bubbleInfo').each(function () {
            var distance = 10;
            // animation time
            var time = 250;
            // delay to hide the bubble info. message
            var hideDelay = 1000;

            var hideDelayTimer = null;

            var beingShown = false;
            var shown = false;
            var trigger = $j('.trigger', this);
            var info = $j('.popup', this).css('opacity', 0);
            
            $j([trigger.get(0), info.get(0)]).focus(function () {
                if (hideDelayTimer) {
                    clearTimeout(hideDelayTimer);
                }
                if (beingShown || shown) {
                    // don't trigger the animation again
                    return;
                } else {
                    // reset position of info box
                    beingShown = true;

                    info.css({
                        top: -33,
                        left: -0,
                        display: 'block'
                    }).animate({
                        top: '-=' + distance + 'px',
                        opacity: 1
                    }, time, 'swing', function() {
                        beingShown = false;
                        shown = true;
                    });
                }

                return false;
            }).blur(function () {
                if (hideDelayTimer) {
                    clearTimeout(hideDelayTimer);
                }
                hideDelayTimer = setTimeout(function () {
                    hideDelayTimer = null;
                    info.animate({
                        top: '-=' + distance + 'px',
                        opacity: 0
                    }, time, 'swing', function () {
                        shown = false;
                        info.css('display', 'none');
                    });

                }, hideDelay);

                return false;
            });
        });
    });
}

/**
 *
 */
function docReady() {

    $j(document).ready(function() {
        $j('input[type="text"]').addClass("idleField");
        $j('input[type="password"]').addClass("idleField");

        // Remove idleField class and adding focusField class to the input type
        // text and password when the input aquire focus
        $j('input[type="text"]').focus(function() {
            $j(this).removeClass("idleField").addClass("focusField");
            //if ($j.trim(this.value) == this.defaultValue){
            if ($j.trim(this.value) == this.title){
                this.value = '';
            }
            if(this.value != this.defaultValue){
                this.select();
            }
        });

        $j('input[type="password"]').focus(function() {
            $j(this).removeClass("idleField").addClass("focusField");
            if ($j.trim(this.value) == this.defaultValue) {
                this.value = '';
            }
            if(this.value != this.defaultValue) {
                this.select();
            }
        });

        // Remove focusField class and adding idleField class to the input type
        // text and password when the input loses the focus
        $j('input[type="text"]').blur(function() {
            $j(this).removeClass("focusField").addClass("idleField");
            if ($j.trim(this.value) == '') {
                //this.value = (this.defaultValue ? this.defaultValue : '');
                this.value = (this.title ? this.title : '');
            }
        });
        $j('input[type="password"]').blur(function() {
            $j(this).removeClass("focusField").addClass("idleField");
            if ($j.trim(this.value) == '') {
                this.value = (this.defaultValue ? this.defaultValue : '');
            }
        });
    });
}


/**
 *
 */
function reBubble(clientId) {
    
    var divId = clientId +':bubble';

    $j(function () {
        $j('.bubbleInfo').each(function () {
            
            if(this.id == divId) {
                var distance = 10;
                // animation time
                var time = 250;
                // delay to hide the bubble info. message
                var hideDelay = 1000;

                var hideDelayTimer = null;

                var beingShown = false;
                var shown = false;
                var trigger = $j('.trigger', this);
                var info = $j('.popup', this).css('opacity', 0);

                info.css({
                    top: -33,
                    left: -0,
                    display: 'block'
                }).animate({
                    top: '-=' + distance + 'px',
                    opacity: 1
                }, time, 'swing', function() {
                    // nothing to do
                    beingShown = false;
                    shown = false;
                });

                $j([trigger.get(0), info.get(0)]).focus(function () {
                    if (hideDelayTimer) {
                        clearTimeout(hideDelayTimer);
                    }
                    if (beingShown || shown) {
                        // don't trigger the animation again
                        return;
                    } else {
                        // reset position of info box
                        beingShown = true;

                        info.css({
                            top: -33,
                            left: -0,
                            display: 'block'
                        }).animate({
                            top: '-=' + distance + 'px',
                            opacity: 1
                        }, time, 'swing', function() {
                            beingShown = false;
                            shown = true;
                        });
                    }

                    return false;
                }).blur(function () {
                    if (hideDelayTimer) {
                        clearTimeout(hideDelayTimer);
                    }
                    hideDelayTimer = setTimeout(function () {
                        hideDelayTimer = null;
                        info.animate({
                            top: '-=' + distance + 'px',
                            opacity: 0
                        }, time, 'swing', function () {
                            shown = false;
                            info.css('display', 'none');
                        });

                    }, hideDelay);

                    return false;
                });
            }
        });
    });
    
}