<?php
// 聚合支付配置文件

return [
    'notify_url' => env('ADAPAY_AGENT_NOTIFY_URL', url('api/systemlink/adapay/agent/callback')),
    'agent_url' => env('ADAPAY_AGENT_URL', 'http://agent.shopex123.com/api/ecshopx'),
    'agent_public_key' => 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRVfRTHRLZOSodX5jhbwfLXGeCtmTxhPd7lxSiFdh4tlb20/JCU0zu1NI9c9gL56MRRURk9FGKEmc+stZQxiCao9lI8MmwzNecKAu8OVxTKSAXy40PZsOs58RrpOiBgk71piMrcaXBg2ji0N0IOVYjbV52ermPA8A5uOSCJ2awOwIDAQAB',
    //短信模版
    'sms_template' => [
        //主商户
        'admin' => "{{法人姓名}}您好:\n您提交的主商户开户申请{{步骤}}已审批完成，请及时查看。",
        //子商户
        'sub' => "{{法人姓名}}您好:\n您所在的{{商户名称}}提交的开户申请已审批完成，请及时查看。",
        //重置经销商密码
        'reset_pwd' => "{{联系人}}您好:\n您所在的{{经销商名称}}在云店后台重置的密码为:{{随机密码}}"
    ],
    
    //商户菜单控制  
    //valid 已开户 invalid 未开户; example: admin->valid 主商户已开户需要禁用的菜单项; dealer->invalid (adapay)经销商未开户需要禁用的菜单项
    //'adapay_merchant' => 2   key表示菜单的alias_name, 数值表示level层级
    'menuControl' => [
        //主商户
        'admin' => [
            'valid' => ['/setting/adapay_merchant' => 2],
            'invalid' => [
                '/shop_dealer' => 1, '/financial/adapay_merchant_info' => 2, '/financial/adapay_member_audit' => 2,'/order/adapay_trades' => 2, '/order/adapay_cash' => 2, '/setting/adapay_cash_setting' => 2],
        ],
        //adapay经销商端
        'dealer' => [
            'valid' => ['/dealer/adapay_member/entry' => 2],
            'invalid' => ['/dealer/adapay_member/info' => 2, '/dealer/trades/adapay_trades' => 2, '/dealer/trades/adapay_cash' => 2],
            'sub_deny' => ['/dealer/setting/account_management' => 2],
        ],
        //店铺端
        'distributor' => [
            'valid' => ['/shopadmin/store/adapay_member_entry' => 2],
            'invalid' => ['/shopadmin/order/adapay_trades' => 2, '/shopadmin/order/adapay_cash' => 2]
        ]
    ],
];
