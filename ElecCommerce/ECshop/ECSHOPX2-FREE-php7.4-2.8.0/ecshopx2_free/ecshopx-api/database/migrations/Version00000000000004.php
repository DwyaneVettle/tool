<?php

namespace Database\Migrations;

use Doctrine\Migrations\AbstractMigration;
use Doctrine\DBAL\Schema\Schema as Schema;
use AdaPayBundle\Services\BankCodeService;
use AdaPayBundle\Services\RegionService;
use AdaPayBundle\Services\WxBusinessService;

class Version00000000000004 extends AbstractMigration
{
    /**
     * @param Schema $schema
     */
    public function up(Schema $schema): void
    {
        $this->addSql('CREATE INDEX idx_companyid_userid ON popularize_promoter (company_id, user_id)');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4AkGpEuVQJqj3EkRjMpYBb8O4U8TzFcnU",phone="95353" where corp_name like "%德邦%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e42VAYZVaoYarrOhvJ5gf4mVNS6VW7zR11",phone="11185" where corp_name like "%中邮物流%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4XN8Ie3FE75t4nc4MxGuU0UqUQyAgDkTX",phone="17827251753" where corp_name like "%飞洋快递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4xPndgZMDIyfeGTWsMJOTcmWvaPQjWPbL",phone="95338" where corp_name like "%顺丰快递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/1de214b5e9a6d2c767fe49eba70fd919jxbPy52QRLsx9jMk7ZeKTICpvleGusQc",phone="" where corp_name like "%速达快递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4aEkk6x65nIszqANAgNmxsAEicv2iUrGK",phone="400-104-0088" where corp_name like "%安能物流%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4eSA3v0EfUmJVdprZ9mFCK5KMfog7KWR4",phone="11183" where corp_name like "%EMS国内%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4o1RBTBrQw412SyH0V7UOWzuQjJsbHwG6",phone="0769-85095572" where corp_name like "%快捷速递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4Gl8K3RvXSlBp8WazgYvTAPKQ9Kczec4r",phone="400-886-1888" where corp_name like "%FedEx联邦快递(中国件)%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e40pbtGzjzwgF9Sxo2HS7h2JxbYmuhvPQj",phone="400-111-1123" where corp_name like "%国通快递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4zWu4C6wUrKlJ6JFz0l29D8Redv5AZFvj",phone="4000-181977" where corp_name like "%高铁速递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4hq5ibyDleEzQ4K7ZgQ7wqtBARKJXKkYY",phone="95320" where corp_name like "%百世汇通%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4PCs9RsZlIuYSzcmXPe27IUoKKWU0lj7P",phone="950616" where corp_name like "%京东快递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4vxUIXYv9cbP8XSJ1EnmQm4HJ2VlU4ZuM",phone="0769-8993-8868" where corp_name like "%龙邦快递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/c56ff9287c1ee5fab9d18ed80e365964pGdrHrnCTvb2BfTn5l72MWGKvoZv5hjT",phone="400-6886-765" where corp_name like "%能达速递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e46JGeZGx8plZvKpquZGmAH9GBKe6I13kZ",phone="400-656-1185" where corp_name like "%速通物流%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4E6Od3QI2ihjpsk7IRXAEaEs4qtE9SHxw",phone="95543" where corp_name like "%申通快递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4VUP1HP65UI8O32kVZJGgTS8y8xhVCnLq",phone="400-881-8106" where corp_name like "%三态速递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4LyjsmSBzHxcrutymzXBBSwnX3qKw1EbT",phone="95349" where corp_name like "%优速快递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e40jfFlSj53OjOjnJgsmbK7uNt8b3uBfqM",phone="400-830-6333" where corp_name like "%信丰快递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4ZmVHoyPO1y8k1Seu1rL3VUqBmW5SiYIZ",phone="95546" where corp_name like "%韵达快递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4V8xaNlyZ624aPG2HPtlKGnT4eWAZfSYX",phone="95554" where corp_name like "%圆通速递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4RHXgbycSFKvKFbUbTanNFj8Z61q9Ujkf",phone="11183" where corp_name like "%邮政平邮/小包%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4gEqQ2K6pTZs5yTqiG3kEFYP4lUza315E",phone="400-6789-000" where corp_name like "%宅急送%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e46aUG1GRVwR9Z1NIlM3H2eOKLl7e5HiEx",phone="95338" where corp_name like "%中铁快运%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4J29I4et70CdHSWkS57PCI89yB8b9Vrwl",phone="95311" where corp_name like "%中通速递%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4B3fJUH1WrpD56eEoXYVEkZCwv4UIaJo3",phone="400-666-1213" where corp_name like "%中铁物流%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4KMH2n2PS0eOn4makfqZEY3fRPevE0WrF",phone="95324" where corp_name like "%跨越速运%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/9777dbbf7f7f7b6074610b45c2e01377wkLDcaR22Hl2nO2JdRebHdbO5B0Bocn6",phone="400-8856-561" where corp_name like "%百世快运%"');
        $this->addSql('update logistics set logo="https://b-img-cdn.yuanyuanke.cn/image/21/2021/05/21/f425f5ae2e6032eb6fded1015fc979e4GEXCUgE7sSoG4OhEdVcoPochAwSisoUz",phone="400-188-8888" where corp_name like "%天天快递%"');
    }

    /**
     * @param Schema $schema
     */
    public function down(Schema $schema): void
    {
    }
}
