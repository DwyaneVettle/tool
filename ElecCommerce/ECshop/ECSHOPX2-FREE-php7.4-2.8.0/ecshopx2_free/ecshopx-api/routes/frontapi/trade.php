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

$api->version('v1', function($api) {
    $api->group(['prefix' => 'h5app', 'namespace' => 'OrdersBundle\Http\FrontApi\V1\Action', 'middleware' => ['dingoguard:h5app', 'api.auth'], 'providers' => 'jwt'], function($api) {

        $api->get('/wxapp/trade/detail', [ 'name'=>'获取支付单详情', 'as' => 'front.h5app.trade.detail', 'uses'=>'Payment@getTradeDetail']);
        
    });

    $api->group(['prefix' => 'h5app', 'namespace' => 'OrdersBundle\Http\FrontApi\V1\Action', 'middleware' => 'frontnoauth:h5app'], function($api) {
        $api->get('/wxapp/trade/setting', [ 'name'=>'获取交易配置信息列表', 'as' => 'front.h5app.trade.setting.list', 'uses'=>'TradeSetting@getSetting']);
    });
    // 支付方式相关
    $api->group(['prefix' => 'h5app', 'namespace' => 'PaymentBundle\Http\FrontApi\V1\Action', 'middleware' => ['dingoguard:h5app', 'api.auth'], 'providers' => 'jwt'], function($api) {
        $api->get('/wxapp/trade/payment/list', [ 'name'=>'获取支付配置信息列表', 'as' => 'front.h5app.payment.setting.list', 'uses'=>'Payment@getPaymentSettingList']);
        $api->get('/wxapp/trade/withdraw/list', [ 'name'=>'获取提现方式列表', 'as' => 'front.h5app.withdraw.setting.list', 'uses'=>'Payment@getWithDrawList']);
        $api->get('/wxapp/trade/payment/hfpayversionstatus', [ 'name'=>'获取汇付天下版本状态', 'as' => 'front.h5app.payment.hfpay.status', 'uses'=>'Payment@getHfpayVersionStatus']);
    });
});
