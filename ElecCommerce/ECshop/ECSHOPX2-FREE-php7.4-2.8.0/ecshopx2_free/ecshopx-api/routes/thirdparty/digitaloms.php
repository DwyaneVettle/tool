<?php
/*
|--------------------------------------------------------------------------
| 神州数码OMS
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
 */

$api->version('v1', function($api) {
    $api->group(['namespace' => 'ThirdPartyBundle\Http\ThirdApi\V1\Action'], function($api) {
        //神州数码回调接口
        $api->post('/digitaloms/callback', ['as' => 'digitaloms.callback', 'uses'=>'DigitalOms@call']);
        //订单审核
        $api->post('/digitaloms/check', ['as' => 'digitaloms.check', 'uses'=>'DigitalOms@check']);
        //发货
        $api->post('/digitaloms/delivery', ['as' => 'digitaloms.delivery', 'uses'=>'DigitalOms@delivery']);
    });
});
