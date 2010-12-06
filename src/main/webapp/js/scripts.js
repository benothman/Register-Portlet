/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$j = jQuery.noConflict();

$j(function () {
    $j('.bubbleInfo').each(function () {
        var distance = 10;
        var time = 250;
        var hideDelay = 500;

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



    // Remove idleField class and adding focusField class to the input type
    // text and password when the input aquire focus
    $j('input[type="text"]').focus(function() {
        $j(this).removeClass("idleField").addClass("focusField");
        if ($j.trim(this.value) == this.defaultValue){
            this.value = '';
        }
        if(this.value != this.defaultValue){
            this.select();
        }
    });
    $j('input[type="password"]').focus(function() {
        $j(this).removeClass("idleField").addClass("focusField");
        if ($j.trim(this.value) == this.defaultValue){
            this.value = '';
        }
        if(this.value != this.defaultValue){
            this.select();
        }
    });

    // Remove focusField class and adding idleField class to the input type
    // text and password when the input loses the focus
    $j('input[type="text"]').blur(function() {
        $j(this).removeClass("focusField").addClass("idleField");
        if ($j.trim(this.value) == ''){
            this.value = (this.defaultValue ? this.defaultValue : '');
        }
    });
    $j('input[type="password"]').blur(function() {
        $j(this).removeClass("focusField").addClass("idleField");
        if ($j.trim(this.value) == ''){
            this.value = (this.defaultValue ? this.defaultValue : '');
        }
    });





});


$j(document).ready(function() {
    $j('input[type="text"]').addClass("idleField");
    $j('input[type="password"]').addClass("idleField");
    
    
});



