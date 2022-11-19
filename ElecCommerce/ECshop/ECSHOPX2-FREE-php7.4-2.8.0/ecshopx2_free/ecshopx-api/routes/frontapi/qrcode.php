<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
 */

/* ↓↓↓↓↓ ↓↓↓↓↓ ↓↓↓↓↓ ↓↓↓↓↓ taro小程序、h5、app端、pc端 ↓↓↓↓↓ ↓↓↓↓↓ ↓↓↓↓↓ ↓↓↓↓↓ */
$api->version('v1', function ($api) {
    $api->group(['prefix' => 'h5app', 'namespace' => 'WechatBundle\Http\Controllers', 'middleware' => 'frontnoauth:h5app'], function ($api) {
        $api->get('/wxapp/pcqrcode', ['as' => 'front.wxapp.pcqrcode', 'uses' => 'Qrcode@getPcQrcode']);
        $api->get('/wxapp/pcloginqrcode', ['as' => 'front.wxapp.pcloginqrcode', 'uses' => 'Qrcode@getPcLoginQrcode']);
    });
});
$app->router->group(['namespace' => 'CommunityBundle\Http\Controllers'], function ($app) {
    // 小程序端获取社区二维码
    $app->get('/wechatAuth/shopwxapp/community/qrcode.png', ['as' => 'front.promotion.community.qrcode',  'uses'=>'Qrcode@getQrcode'] );
});
$app->router->group(['namespace' => 'WechatBundle\Http\Controllers'], function ($app) {
    // 获取小程序码
    $app->get('/wechatAuth/wxapp/qrcode.png', ['as' => 'front.wxapp.qrcode',  'uses'=>'Qrcode@getQrcode'] );
});
/* ↑↑↑↑↑ ↑↑↑↑↑ ↑↑↑↑↑ ↑↑↑↑↑ taro小程序、h5、app端、pc端 ↑↑↑↑↑ ↑↑↑↑↑ ↑↑↑↑↑ ↑↑↑↑↑ */
