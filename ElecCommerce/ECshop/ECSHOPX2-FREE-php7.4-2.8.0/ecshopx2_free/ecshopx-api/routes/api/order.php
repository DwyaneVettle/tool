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

$api->version('v1', function ($api) {
    $api->group(['namespace' => 'OrdersBundle\Http\Api\V1\Action', 'middleware' => ['api.auth', 'shoplog'], 'providers' => 'jwt'], function ($api) {
        $api->get('/rights/getdata', ['name' => '获取用户权益', 'middleware' => 'activated', 'as' => 'order.rights.list', 'uses' => 'Rights@getRightsListData']);
        $api->get('/rights/list', ['name' => '获取权益列表', 'middleware' => ['activated', 'datapass'], 'as' => 'order.rights.list.get', 'uses' => 'Rights@getRightsList']);
        $api->post('/rights', ['name' => '新增权益', 'middleware' => 'activated', 'as' => 'order.rights.add', 'uses' => 'Rights@createRights']);
        $api->put('/transfer/rights', ['name' => '转赠会员权益', 'middleware' => 'activated', 'as' => 'order.rights.transfer', 'uses' => 'Rights@transferRights']);
        $api->get('/transfer/rights/list', ['name' => '转赠会员权益列表', 'middleware' => ['activated', 'datapass'], 'as' => 'order.rights.transfer.list', 'uses' => 'Rights@transferRightsList']);

        //获取权益核销记录
        $api->get('/rights/log', ['name' => '获取权益核销列表', 'middleware' => ['activated', 'datapass'], 'as' => 'rights.log.list', 'uses' => 'RightsLogs@getLogsList']);
        //权益延期
        $api->get('/rights/info', ['name' => '获取权益核销详情', 'middleware' => 'activated', 'as' => 'rights.info', 'uses' => 'Rights@getRightsInfo']);
        $api->post('/rights/delay', ['name' => '权益延期', 'middleware' => 'activated', 'as' => 'rights.delay', 'uses' => 'Rights@delayRights']);

        // 订单物流日志
        $api->get('/delivery/process/list', ['name' => '订单物流日志', 'middleware' => 'activated', 'as' => 'delivery.process.list', 'uses' => 'Delivery@processLogList']);
    });
    // 运费模板相关接口
    $api->group(['namespace' => 'OrdersBundle\Http\Api\V1\Action', 'middleware' => ['api.auth', 'shoplog'], 'providers' => 'jwt'], function ($api) {
        $api->get('/shipping/templates/list', ['name' => '获取运费模板列表', 'middleware' => 'activated', 'as' => 'shipping.templates.list', 'uses' => 'ShippingTemplate@getShippingTemplatesList']);
        $api->get('/shipping/templates/info/{id}', ['name' => '获取运费模板详情', 'middleware' => 'activated', 'as' => 'shipping.templates.info', 'uses' => 'ShippingTemplate@getShippingTemplatesInfo']);
        $api->post('/shipping/templates/create', ['name' => '添加运费模板', 'middleware' => 'activated', 'as' => 'shipping.templates.create', 'uses' => 'ShippingTemplate@createShippingTemplates']);
        $api->put('/shipping/templates/update/{id}', ['name' => '更新运费模板', 'middleware' => 'activated', 'as' => 'shipping.templates.update', 'uses' => 'ShippingTemplate@updateShippingTemplates']);
        $api->delete('/shipping/templates/delete/{id}', ['name' => '删除运费模板', 'middleware' => 'activated', 'as' => 'shipping.templates.delete', 'uses' => 'ShippingTemplate@deleteShippingTemplates']);


        $api->get('/trade/logistics/list', ['name' => '获取可用物流列表', 'middleware' => 'activated', 'as' => 'trade.logistics.list', 'uses' => 'CompanyRelLogistics@getLogisticsList']);
        $api->get('/company/logistics/list', ['name' => '获取公司启用物流列表', 'middleware' => 'activated', 'as' => 'company.logistics.list', 'uses' => 'CompanyRelLogistics@getCompanyLogisticsList']);
        $api->post('/company/logistics/create', ['name' => '创建公司启用物流', 'middleware' => 'activated', 'as' => 'company.logistics.create', 'uses' => 'CompanyRelLogistics@createCompanyLogistics']);
        $api->delete('/company/logistics/{id}', ['name' => '删除公司关闭物流', 'middleware' => 'activated', 'as' => 'company.logistics.delete', 'uses' => 'CompanyRelLogistics@deleteCompanyLogistics']);
        $api->get('/company/logistics/qinglongcode', ['name' => '设置公司青龙物流编码', 'middleware' => 'activated', 'as' => 'company.logistics.qinglongcode.info', 'uses' => 'CompanyRelLogistics@getQinglongcode']);
        $api->post('/company/logistics/qinglongcode', ['name' => '设置公司青龙物流编码', 'middleware' => 'activated', 'as' => 'company.logistics.qinglongcode', 'uses' => 'CompanyRelLogistics@setQinglongcode']);
    });

    $api->group(['namespace' => 'OrdersBundle\Http\Api\V1\Action', 'middleware' => ['api.auth', 'shoplog', 'activated'], 'providers' => 'jwt'], function ($api) {
        $api->post('/company/dada/create', ['name' => '创建公司启用达达同城配', 'as' => 'company.dada.create', 'uses' => 'CompanyRelDada@createCompanyRelDada']);
        $api->get('/company/dada/info', ['name' => '获取公司达达同城配信息', 'as' => 'company.dada.info', 'uses' => 'CompanyRelDada@getCompanyRelDadaInfo']);
        $api->get('/company/delivery', ['name' => '获取商户同城配商家自配信息', 'as' => 'company.delivery.info', 'uses' => 'CompanyRelDeliveryController@getInfo']);
        $api->post('/company/delivery', ['name' => '更新商户同城配商家自配信息', 'as' => 'company.delivery.save', 'uses' => 'CompanyRelDeliveryController@save']);
    });

    $api->group(['namespace' => 'OrdersBundle\Http\Api\V1\Action', 'middleware' => ['api.auth', 'shoplog'], 'providers' => 'jwt'], function ($api) {
        $api->get('/order/message/new', ['name' => '店务端未读消息', 'middleware' => 'activated', 'as' => 'order.message.new', 'uses' => 'OrderMessage@getNewInfo']);
        $api->get('/order/message/list', ['name' => '店务端消息列表', 'middleware' => 'activated', 'as' => 'order.message.list', 'uses' => 'OrderMessage@getList']);
        $api->post('/order/message/update', ['name' => '店务端更新消息', 'middleware' => 'activated', 'as' => 'order.message.update', 'uses' => 'OrderMessage@updateMsg']);
    });
});
