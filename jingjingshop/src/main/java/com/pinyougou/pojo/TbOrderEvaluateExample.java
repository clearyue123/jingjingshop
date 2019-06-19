package com.pinyougou.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TbOrderEvaluateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbOrderEvaluateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * 
     * 
     * @author wcyong
     * 
     * @date 2019-05-20
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Long value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Long value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Long value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Long value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Long> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Long> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Long value1, Long value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgIsNull() {
            addCriterion("goods_evaluate_msg is null");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgIsNotNull() {
            addCriterion("goods_evaluate_msg is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgEqualTo(String value) {
            addCriterion("goods_evaluate_msg =", value, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgNotEqualTo(String value) {
            addCriterion("goods_evaluate_msg <>", value, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgGreaterThan(String value) {
            addCriterion("goods_evaluate_msg >", value, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgGreaterThanOrEqualTo(String value) {
            addCriterion("goods_evaluate_msg >=", value, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgLessThan(String value) {
            addCriterion("goods_evaluate_msg <", value, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgLessThanOrEqualTo(String value) {
            addCriterion("goods_evaluate_msg <=", value, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgLike(String value) {
            addCriterion("goods_evaluate_msg like", value, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgNotLike(String value) {
            addCriterion("goods_evaluate_msg not like", value, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgIn(List<String> values) {
            addCriterion("goods_evaluate_msg in", values, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgNotIn(List<String> values) {
            addCriterion("goods_evaluate_msg not in", values, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgBetween(String value1, String value2) {
            addCriterion("goods_evaluate_msg between", value1, value2, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateMsgNotBetween(String value1, String value2) {
            addCriterion("goods_evaluate_msg not between", value1, value2, "goodsEvaluateMsg");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreIsNull() {
            addCriterion("goods_evaluate_score is null");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreIsNotNull() {
            addCriterion("goods_evaluate_score is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreEqualTo(Float value) {
            addCriterion("goods_evaluate_score =", value, "goodsEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreNotEqualTo(Float value) {
            addCriterion("goods_evaluate_score <>", value, "goodsEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreGreaterThan(Float value) {
            addCriterion("goods_evaluate_score >", value, "goodsEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("goods_evaluate_score >=", value, "goodsEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreLessThan(Float value) {
            addCriterion("goods_evaluate_score <", value, "goodsEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreLessThanOrEqualTo(Float value) {
            addCriterion("goods_evaluate_score <=", value, "goodsEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreIn(List<Float> values) {
            addCriterion("goods_evaluate_score in", values, "goodsEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreNotIn(List<Float> values) {
            addCriterion("goods_evaluate_score not in", values, "goodsEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreBetween(Float value1, Float value2) {
            addCriterion("goods_evaluate_score between", value1, value2, "goodsEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andGoodsEvaluateScoreNotBetween(Float value1, Float value2) {
            addCriterion("goods_evaluate_score not between", value1, value2, "goodsEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreIsNull() {
            addCriterion("seller_evaluate_score is null");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreIsNotNull() {
            addCriterion("seller_evaluate_score is not null");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreEqualTo(Float value) {
            addCriterion("seller_evaluate_score =", value, "sellerEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreNotEqualTo(Float value) {
            addCriterion("seller_evaluate_score <>", value, "sellerEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreGreaterThan(Float value) {
            addCriterion("seller_evaluate_score >", value, "sellerEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("seller_evaluate_score >=", value, "sellerEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreLessThan(Float value) {
            addCriterion("seller_evaluate_score <", value, "sellerEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreLessThanOrEqualTo(Float value) {
            addCriterion("seller_evaluate_score <=", value, "sellerEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreIn(List<Float> values) {
            addCriterion("seller_evaluate_score in", values, "sellerEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreNotIn(List<Float> values) {
            addCriterion("seller_evaluate_score not in", values, "sellerEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreBetween(Float value1, Float value2) {
            addCriterion("seller_evaluate_score between", value1, value2, "sellerEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andSellerEvaluateScoreNotBetween(Float value1, Float value2) {
            addCriterion("seller_evaluate_score not between", value1, value2, "sellerEvaluateScore");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreIsNull() {
            addCriterion("ship_speed_score is null");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreIsNotNull() {
            addCriterion("ship_speed_score is not null");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreEqualTo(Float value) {
            addCriterion("ship_speed_score =", value, "shipSpeedScore");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreNotEqualTo(Float value) {
            addCriterion("ship_speed_score <>", value, "shipSpeedScore");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreGreaterThan(Float value) {
            addCriterion("ship_speed_score >", value, "shipSpeedScore");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("ship_speed_score >=", value, "shipSpeedScore");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreLessThan(Float value) {
            addCriterion("ship_speed_score <", value, "shipSpeedScore");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreLessThanOrEqualTo(Float value) {
            addCriterion("ship_speed_score <=", value, "shipSpeedScore");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreIn(List<Float> values) {
            addCriterion("ship_speed_score in", values, "shipSpeedScore");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreNotIn(List<Float> values) {
            addCriterion("ship_speed_score not in", values, "shipSpeedScore");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreBetween(Float value1, Float value2) {
            addCriterion("ship_speed_score between", value1, value2, "shipSpeedScore");
            return (Criteria) this;
        }

        public Criteria andShipSpeedScoreNotBetween(Float value1, Float value2) {
            addCriterion("ship_speed_score not between", value1, value2, "shipSpeedScore");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreIsNull() {
            addCriterion("ship_service_score is null");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreIsNotNull() {
            addCriterion("ship_service_score is not null");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreEqualTo(Float value) {
            addCriterion("ship_service_score =", value, "shipServiceScore");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreNotEqualTo(Float value) {
            addCriterion("ship_service_score <>", value, "shipServiceScore");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreGreaterThan(Float value) {
            addCriterion("ship_service_score >", value, "shipServiceScore");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("ship_service_score >=", value, "shipServiceScore");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreLessThan(Float value) {
            addCriterion("ship_service_score <", value, "shipServiceScore");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreLessThanOrEqualTo(Float value) {
            addCriterion("ship_service_score <=", value, "shipServiceScore");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreIn(List<Float> values) {
            addCriterion("ship_service_score in", values, "shipServiceScore");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreNotIn(List<Float> values) {
            addCriterion("ship_service_score not in", values, "shipServiceScore");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreBetween(Float value1, Float value2) {
            addCriterion("ship_service_score between", value1, value2, "shipServiceScore");
            return (Criteria) this;
        }

        public Criteria andShipServiceScoreNotBetween(Float value1, Float value2) {
            addCriterion("ship_service_score not between", value1, value2, "shipServiceScore");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateIsNull() {
            addCriterion("evaluate_date is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateIsNotNull() {
            addCriterion("evaluate_date is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateEqualTo(Date value) {
            addCriterionForJDBCDate("evaluate_date =", value, "evaluateDate");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("evaluate_date <>", value, "evaluateDate");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("evaluate_date >", value, "evaluateDate");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("evaluate_date >=", value, "evaluateDate");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateLessThan(Date value) {
            addCriterionForJDBCDate("evaluate_date <", value, "evaluateDate");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("evaluate_date <=", value, "evaluateDate");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateIn(List<Date> values) {
            addCriterionForJDBCDate("evaluate_date in", values, "evaluateDate");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("evaluate_date not in", values, "evaluateDate");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("evaluate_date between", value1, value2, "evaluateDate");
            return (Criteria) this;
        }

        public Criteria andEvaluateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("evaluate_date not between", value1, value2, "evaluateDate");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(String value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(String value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(String value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(String value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(String value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(String value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLike(String value) {
            addCriterion("is_deleted like", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotLike(String value) {
            addCriterion("is_deleted not like", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<String> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<String> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(String value1, String value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(String value1, String value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreIsNull() {
            addCriterion("goods_package_score is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreIsNotNull() {
            addCriterion("goods_package_score is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreEqualTo(Float value) {
            addCriterion("goods_package_score =", value, "goodsPackageScore");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreNotEqualTo(Float value) {
            addCriterion("goods_package_score <>", value, "goodsPackageScore");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreGreaterThan(Float value) {
            addCriterion("goods_package_score >", value, "goodsPackageScore");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("goods_package_score >=", value, "goodsPackageScore");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreLessThan(Float value) {
            addCriterion("goods_package_score <", value, "goodsPackageScore");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreLessThanOrEqualTo(Float value) {
            addCriterion("goods_package_score <=", value, "goodsPackageScore");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreIn(List<Float> values) {
            addCriterion("goods_package_score in", values, "goodsPackageScore");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreNotIn(List<Float> values) {
            addCriterion("goods_package_score not in", values, "goodsPackageScore");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreBetween(Float value1, Float value2) {
            addCriterion("goods_package_score between", value1, value2, "goodsPackageScore");
            return (Criteria) this;
        }

        public Criteria andGoodsPackageScoreNotBetween(Float value1, Float value2) {
            addCriterion("goods_package_score not between", value1, value2, "goodsPackageScore");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 
     * @author wcyong
     * 
     * @date 2019-05-20
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}