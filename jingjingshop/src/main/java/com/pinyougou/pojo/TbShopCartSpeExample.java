package com.pinyougou.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbShopCartSpeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbShopCartSpeExample() {
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
     * @date 2019-05-07
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

        public Criteria andCartIdIsNull() {
            addCriterion("cart_id is null");
            return (Criteria) this;
        }

        public Criteria andCartIdIsNotNull() {
            addCriterion("cart_id is not null");
            return (Criteria) this;
        }

        public Criteria andCartIdEqualTo(Long value) {
            addCriterion("cart_id =", value, "cartId");
            return (Criteria) this;
        }

        public Criteria andCartIdNotEqualTo(Long value) {
            addCriterion("cart_id <>", value, "cartId");
            return (Criteria) this;
        }

        public Criteria andCartIdGreaterThan(Long value) {
            addCriterion("cart_id >", value, "cartId");
            return (Criteria) this;
        }

        public Criteria andCartIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cart_id >=", value, "cartId");
            return (Criteria) this;
        }

        public Criteria andCartIdLessThan(Long value) {
            addCriterion("cart_id <", value, "cartId");
            return (Criteria) this;
        }

        public Criteria andCartIdLessThanOrEqualTo(Long value) {
            addCriterion("cart_id <=", value, "cartId");
            return (Criteria) this;
        }

        public Criteria andCartIdIn(List<Long> values) {
            addCriterion("cart_id in", values, "cartId");
            return (Criteria) this;
        }

        public Criteria andCartIdNotIn(List<Long> values) {
            addCriterion("cart_id not in", values, "cartId");
            return (Criteria) this;
        }

        public Criteria andCartIdBetween(Long value1, Long value2) {
            addCriterion("cart_id between", value1, value2, "cartId");
            return (Criteria) this;
        }

        public Criteria andCartIdNotBetween(Long value1, Long value2) {
            addCriterion("cart_id not between", value1, value2, "cartId");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNull() {
            addCriterion("spe_id is null");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNotNull() {
            addCriterion("spe_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpeIdEqualTo(Long value) {
            addCriterion("spe_id =", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotEqualTo(Long value) {
            addCriterion("spe_id <>", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThan(Long value) {
            addCriterion("spe_id >", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("spe_id >=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThan(Long value) {
            addCriterion("spe_id <", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThanOrEqualTo(Long value) {
            addCriterion("spe_id <=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdIn(List<Long> values) {
            addCriterion("spe_id in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotIn(List<Long> values) {
            addCriterion("spe_id not in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdBetween(Long value1, Long value2) {
            addCriterion("spe_id between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotBetween(Long value1, Long value2) {
            addCriterion("spe_id not between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdIsNull() {
            addCriterion("spe_op_id is null");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdIsNotNull() {
            addCriterion("spe_op_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdEqualTo(Long value) {
            addCriterion("spe_op_id =", value, "speOpId");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdNotEqualTo(Long value) {
            addCriterion("spe_op_id <>", value, "speOpId");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdGreaterThan(Long value) {
            addCriterion("spe_op_id >", value, "speOpId");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("spe_op_id >=", value, "speOpId");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdLessThan(Long value) {
            addCriterion("spe_op_id <", value, "speOpId");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdLessThanOrEqualTo(Long value) {
            addCriterion("spe_op_id <=", value, "speOpId");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdIn(List<Long> values) {
            addCriterion("spe_op_id in", values, "speOpId");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdNotIn(List<Long> values) {
            addCriterion("spe_op_id not in", values, "speOpId");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdBetween(Long value1, Long value2) {
            addCriterion("spe_op_id between", value1, value2, "speOpId");
            return (Criteria) this;
        }

        public Criteria andSpeOpIdNotBetween(Long value1, Long value2) {
            addCriterion("spe_op_id not between", value1, value2, "speOpId");
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
     * @date 2019-05-07
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