<?php

namespace MembersBundle\Entities;

/**
 * MembersSmsLog
 */
class MembersSmsLog
{
    /**
     * @var integer
     */
    private $log_id;

    /**
     * @var integer
     */
    private $company_id;

    /**
     * @var string
     */
    private $send_to_phones;

    /**
     * @var string
     */
    private $sms_content;

    /**
     * @var string
     */
    private $operator;

    /**
     * @var integer
     */
    private $status;

    /**
     * @var integer
     */
    private $created;

    /**
     * @var integer
     */
    private $updated;


    /**
     * Get logId
     *
     * @return integer
     */
    public function getLogId()
    {
        return $this->log_id;
    }

    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return MembersSmsLog
     */
    public function setCompanyId($companyId)
    {
        $this->company_id = $companyId;

        return $this;
    }

    /**
     * Get companyId
     *
     * @return integer
     */
    public function getCompanyId()
    {
        return $this->company_id;
    }

    /**
     * Set sendToPhones
     *
     * @param string $sendToPhones
     *
     * @return MembersSmsLog
     */
    public function setSendToPhones($sendToPhones)
    {
        $this->send_to_phones = $sendToPhones;

        return $this;
    }

    /**
     * Get sendToPhones
     *
     * @return string
     */
    public function getSendToPhones()
    {
        return $this->send_to_phones;
    }

    /**
     * Set smsContent
     *
     * @param string $smsContent
     *
     * @return MembersSmsLog
     */
    public function setSmsContent($smsContent)
    {
        $this->sms_content = $smsContent;

        return $this;
    }

    /**
     * Get smsContent
     *
     * @return string
     */
    public function getSmsContent()
    {
        return $this->sms_content;
    }

    /**
     * Set operator
     *
     * @param string $operator
     *
     * @return MembersSmsLog
     */
    public function setOperator($operator)
    {
        $this->operator = $operator;

        return $this;
    }

    /**
     * Get operator
     *
     * @return string
     */
    public function getOperator()
    {
        return $this->operator;
    }

    /**
     * Set status
     *
     * @param integer $status
     *
     * @return MembersSmsLog
     */
    public function setStatus($status)
    {
        $this->status = $status;

        return $this;
    }

    /**
     * Get status
     *
     * @return integer
     */
    public function getStatus()
    {
        return $this->status;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return MembersSmsLog
     */
    public function setCreated($created)
    {
        $this->created = $created;

        return $this;
    }

    /**
     * Get created
     *
     * @return integer
     */
    public function getCreated()
    {
        return $this->created;
    }

    /**
     * Set updated
     *
     * @param integer $updated
     *
     * @return MembersSmsLog
     */
    public function setUpdated($updated)
    {
        $this->updated = $updated;

        return $this;
    }

    /**
     * Get updated
     *
     * @return integer
     */
    public function getUpdated()
    {
        return $this->updated;
    }
}
