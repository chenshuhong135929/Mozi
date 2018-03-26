package com.sy.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipmentDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipmentDataExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
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

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andHeartrateIsNull() {
            addCriterion("heartrate is null");
            return (Criteria) this;
        }

        public Criteria andHeartrateIsNotNull() {
            addCriterion("heartrate is not null");
            return (Criteria) this;
        }

        public Criteria andHeartrateEqualTo(Integer value) {
            addCriterion("heartrate =", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateNotEqualTo(Integer value) {
            addCriterion("heartrate <>", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateGreaterThan(Integer value) {
            addCriterion("heartrate >", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateGreaterThanOrEqualTo(Integer value) {
            addCriterion("heartrate >=", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateLessThan(Integer value) {
            addCriterion("heartrate <", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateLessThanOrEqualTo(Integer value) {
            addCriterion("heartrate <=", value, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateIn(List<Integer> values) {
            addCriterion("heartrate in", values, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateNotIn(List<Integer> values) {
            addCriterion("heartrate not in", values, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateBetween(Integer value1, Integer value2) {
            addCriterion("heartrate between", value1, value2, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHeartrateNotBetween(Integer value1, Integer value2) {
            addCriterion("heartrate not between", value1, value2, "heartrate");
            return (Criteria) this;
        }

        public Criteria andHighpressureIsNull() {
            addCriterion("highpressure is null");
            return (Criteria) this;
        }

        public Criteria andHighpressureIsNotNull() {
            addCriterion("highpressure is not null");
            return (Criteria) this;
        }

        public Criteria andHighpressureEqualTo(String value) {
            addCriterion("highpressure =", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureNotEqualTo(String value) {
            addCriterion("highpressure <>", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureGreaterThan(String value) {
            addCriterion("highpressure >", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureGreaterThanOrEqualTo(String value) {
            addCriterion("highpressure >=", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureLessThan(String value) {
            addCriterion("highpressure <", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureLessThanOrEqualTo(String value) {
            addCriterion("highpressure <=", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureLike(String value) {
            addCriterion("highpressure like", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureNotLike(String value) {
            addCriterion("highpressure not like", value, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureIn(List<String> values) {
            addCriterion("highpressure in", values, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureNotIn(List<String> values) {
            addCriterion("highpressure not in", values, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureBetween(String value1, String value2) {
            addCriterion("highpressure between", value1, value2, "highpressure");
            return (Criteria) this;
        }

        public Criteria andHighpressureNotBetween(String value1, String value2) {
            addCriterion("highpressure not between", value1, value2, "highpressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureIsNull() {
            addCriterion("bottompressure is null");
            return (Criteria) this;
        }

        public Criteria andBottompressureIsNotNull() {
            addCriterion("bottompressure is not null");
            return (Criteria) this;
        }

        public Criteria andBottompressureEqualTo(String value) {
            addCriterion("bottompressure =", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureNotEqualTo(String value) {
            addCriterion("bottompressure <>", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureGreaterThan(String value) {
            addCriterion("bottompressure >", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureGreaterThanOrEqualTo(String value) {
            addCriterion("bottompressure >=", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureLessThan(String value) {
            addCriterion("bottompressure <", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureLessThanOrEqualTo(String value) {
            addCriterion("bottompressure <=", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureLike(String value) {
            addCriterion("bottompressure like", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureNotLike(String value) {
            addCriterion("bottompressure not like", value, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureIn(List<String> values) {
            addCriterion("bottompressure in", values, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureNotIn(List<String> values) {
            addCriterion("bottompressure not in", values, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureBetween(String value1, String value2) {
            addCriterion("bottompressure between", value1, value2, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBottompressureNotBetween(String value1, String value2) {
            addCriterion("bottompressure not between", value1, value2, "bottompressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureIsNull() {
            addCriterion("bloodpressure is null");
            return (Criteria) this;
        }

        public Criteria andBloodpressureIsNotNull() {
            addCriterion("bloodpressure is not null");
            return (Criteria) this;
        }

        public Criteria andBloodpressureEqualTo(String value) {
            addCriterion("bloodpressure =", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureNotEqualTo(String value) {
            addCriterion("bloodpressure <>", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureGreaterThan(String value) {
            addCriterion("bloodpressure >", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureGreaterThanOrEqualTo(String value) {
            addCriterion("bloodpressure >=", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureLessThan(String value) {
            addCriterion("bloodpressure <", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureLessThanOrEqualTo(String value) {
            addCriterion("bloodpressure <=", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureLike(String value) {
            addCriterion("bloodpressure like", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureNotLike(String value) {
            addCriterion("bloodpressure not like", value, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureIn(List<String> values) {
            addCriterion("bloodpressure in", values, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureNotIn(List<String> values) {
            addCriterion("bloodpressure not in", values, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureBetween(String value1, String value2) {
            addCriterion("bloodpressure between", value1, value2, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andBloodpressureNotBetween(String value1, String value2) {
            addCriterion("bloodpressure not between", value1, value2, "bloodpressure");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationIsNull() {
            addCriterion("mocrocirculation is null");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationIsNotNull() {
            addCriterion("mocrocirculation is not null");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationEqualTo(String value) {
            addCriterion("mocrocirculation =", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationNotEqualTo(String value) {
            addCriterion("mocrocirculation <>", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationGreaterThan(String value) {
            addCriterion("mocrocirculation >", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationGreaterThanOrEqualTo(String value) {
            addCriterion("mocrocirculation >=", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationLessThan(String value) {
            addCriterion("mocrocirculation <", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationLessThanOrEqualTo(String value) {
            addCriterion("mocrocirculation <=", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationLike(String value) {
            addCriterion("mocrocirculation like", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationNotLike(String value) {
            addCriterion("mocrocirculation not like", value, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationIn(List<String> values) {
            addCriterion("mocrocirculation in", values, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationNotIn(List<String> values) {
            addCriterion("mocrocirculation not in", values, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationBetween(String value1, String value2) {
            addCriterion("mocrocirculation between", value1, value2, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andMocrocirculationNotBetween(String value1, String value2) {
            addCriterion("mocrocirculation not between", value1, value2, "mocrocirculation");
            return (Criteria) this;
        }

        public Criteria andBreatheIsNull() {
            addCriterion("breathe is null");
            return (Criteria) this;
        }

        public Criteria andBreatheIsNotNull() {
            addCriterion("breathe is not null");
            return (Criteria) this;
        }

        public Criteria andBreatheEqualTo(String value) {
            addCriterion("breathe =", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheNotEqualTo(String value) {
            addCriterion("breathe <>", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheGreaterThan(String value) {
            addCriterion("breathe >", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheGreaterThanOrEqualTo(String value) {
            addCriterion("breathe >=", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheLessThan(String value) {
            addCriterion("breathe <", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheLessThanOrEqualTo(String value) {
            addCriterion("breathe <=", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheLike(String value) {
            addCriterion("breathe like", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheNotLike(String value) {
            addCriterion("breathe not like", value, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheIn(List<String> values) {
            addCriterion("breathe in", values, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheNotIn(List<String> values) {
            addCriterion("breathe not in", values, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheBetween(String value1, String value2) {
            addCriterion("breathe between", value1, value2, "breathe");
            return (Criteria) this;
        }

        public Criteria andBreatheNotBetween(String value1, String value2) {
            addCriterion("breathe not between", value1, value2, "breathe");
            return (Criteria) this;
        }

        public Criteria andSleepingIsNull() {
            addCriterion("sleeping is null");
            return (Criteria) this;
        }

        public Criteria andSleepingIsNotNull() {
            addCriterion("sleeping is not null");
            return (Criteria) this;
        }

        public Criteria andSleepingEqualTo(String value) {
            addCriterion("sleeping =", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingNotEqualTo(String value) {
            addCriterion("sleeping <>", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingGreaterThan(String value) {
            addCriterion("sleeping >", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingGreaterThanOrEqualTo(String value) {
            addCriterion("sleeping >=", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingLessThan(String value) {
            addCriterion("sleeping <", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingLessThanOrEqualTo(String value) {
            addCriterion("sleeping <=", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingLike(String value) {
            addCriterion("sleeping like", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingNotLike(String value) {
            addCriterion("sleeping not like", value, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingIn(List<String> values) {
            addCriterion("sleeping in", values, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingNotIn(List<String> values) {
            addCriterion("sleeping not in", values, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingBetween(String value1, String value2) {
            addCriterion("sleeping between", value1, value2, "sleeping");
            return (Criteria) this;
        }

        public Criteria andSleepingNotBetween(String value1, String value2) {
            addCriterion("sleeping not between", value1, value2, "sleeping");
            return (Criteria) this;
        }

        public Criteria andStepWhenIsNull() {
            addCriterion("step_when is null");
            return (Criteria) this;
        }

        public Criteria andStepWhenIsNotNull() {
            addCriterion("step_when is not null");
            return (Criteria) this;
        }

        public Criteria andStepWhenEqualTo(Integer value) {
            addCriterion("step_when =", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenNotEqualTo(Integer value) {
            addCriterion("step_when <>", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenGreaterThan(Integer value) {
            addCriterion("step_when >", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenGreaterThanOrEqualTo(Integer value) {
            addCriterion("step_when >=", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenLessThan(Integer value) {
            addCriterion("step_when <", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenLessThanOrEqualTo(Integer value) {
            addCriterion("step_when <=", value, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenIn(List<Integer> values) {
            addCriterion("step_when in", values, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenNotIn(List<Integer> values) {
            addCriterion("step_when not in", values, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenBetween(Integer value1, Integer value2) {
            addCriterion("step_when between", value1, value2, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andStepWhenNotBetween(Integer value1, Integer value2) {
            addCriterion("step_when not between", value1, value2, "stepWhen");
            return (Criteria) this;
        }

        public Criteria andCarrieroadIsNull() {
            addCriterion("carrieroad is null");
            return (Criteria) this;
        }

        public Criteria andCarrieroadIsNotNull() {
            addCriterion("carrieroad is not null");
            return (Criteria) this;
        }

        public Criteria andCarrieroadEqualTo(String value) {
            addCriterion("carrieroad =", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadNotEqualTo(String value) {
            addCriterion("carrieroad <>", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadGreaterThan(String value) {
            addCriterion("carrieroad >", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadGreaterThanOrEqualTo(String value) {
            addCriterion("carrieroad >=", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadLessThan(String value) {
            addCriterion("carrieroad <", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadLessThanOrEqualTo(String value) {
            addCriterion("carrieroad <=", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadLike(String value) {
            addCriterion("carrieroad like", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadNotLike(String value) {
            addCriterion("carrieroad not like", value, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadIn(List<String> values) {
            addCriterion("carrieroad in", values, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadNotIn(List<String> values) {
            addCriterion("carrieroad not in", values, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadBetween(String value1, String value2) {
            addCriterion("carrieroad between", value1, value2, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andCarrieroadNotBetween(String value1, String value2) {
            addCriterion("carrieroad not between", value1, value2, "carrieroad");
            return (Criteria) this;
        }

        public Criteria andSedentaryIsNull() {
            addCriterion("sedentary is null");
            return (Criteria) this;
        }

        public Criteria andSedentaryIsNotNull() {
            addCriterion("sedentary is not null");
            return (Criteria) this;
        }

        public Criteria andSedentaryEqualTo(String value) {
            addCriterion("sedentary =", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryNotEqualTo(String value) {
            addCriterion("sedentary <>", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryGreaterThan(String value) {
            addCriterion("sedentary >", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryGreaterThanOrEqualTo(String value) {
            addCriterion("sedentary >=", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryLessThan(String value) {
            addCriterion("sedentary <", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryLessThanOrEqualTo(String value) {
            addCriterion("sedentary <=", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryLike(String value) {
            addCriterion("sedentary like", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryNotLike(String value) {
            addCriterion("sedentary not like", value, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryIn(List<String> values) {
            addCriterion("sedentary in", values, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryNotIn(List<String> values) {
            addCriterion("sedentary not in", values, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryBetween(String value1, String value2) {
            addCriterion("sedentary between", value1, value2, "sedentary");
            return (Criteria) this;
        }

        public Criteria andSedentaryNotBetween(String value1, String value2) {
            addCriterion("sedentary not between", value1, value2, "sedentary");
            return (Criteria) this;
        }

        public Criteria andMovementstateIsNull() {
            addCriterion("movementstate is null");
            return (Criteria) this;
        }

        public Criteria andMovementstateIsNotNull() {
            addCriterion("movementstate is not null");
            return (Criteria) this;
        }

        public Criteria andMovementstateEqualTo(String value) {
            addCriterion("movementstate =", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateNotEqualTo(String value) {
            addCriterion("movementstate <>", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateGreaterThan(String value) {
            addCriterion("movementstate >", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateGreaterThanOrEqualTo(String value) {
            addCriterion("movementstate >=", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateLessThan(String value) {
            addCriterion("movementstate <", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateLessThanOrEqualTo(String value) {
            addCriterion("movementstate <=", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateLike(String value) {
            addCriterion("movementstate like", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateNotLike(String value) {
            addCriterion("movementstate not like", value, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateIn(List<String> values) {
            addCriterion("movementstate in", values, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateNotIn(List<String> values) {
            addCriterion("movementstate not in", values, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateBetween(String value1, String value2) {
            addCriterion("movementstate between", value1, value2, "movementstate");
            return (Criteria) this;
        }

        public Criteria andMovementstateNotBetween(String value1, String value2) {
            addCriterion("movementstate not between", value1, value2, "movementstate");
            return (Criteria) this;
        }

        public Criteria andBodytempIsNull() {
            addCriterion("bodytemp is null");
            return (Criteria) this;
        }

        public Criteria andBodytempIsNotNull() {
            addCriterion("bodytemp is not null");
            return (Criteria) this;
        }

        public Criteria andBodytempEqualTo(String value) {
            addCriterion("bodytemp =", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempNotEqualTo(String value) {
            addCriterion("bodytemp <>", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempGreaterThan(String value) {
            addCriterion("bodytemp >", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempGreaterThanOrEqualTo(String value) {
            addCriterion("bodytemp >=", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempLessThan(String value) {
            addCriterion("bodytemp <", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempLessThanOrEqualTo(String value) {
            addCriterion("bodytemp <=", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempLike(String value) {
            addCriterion("bodytemp like", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempNotLike(String value) {
            addCriterion("bodytemp not like", value, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempIn(List<String> values) {
            addCriterion("bodytemp in", values, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempNotIn(List<String> values) {
            addCriterion("bodytemp not in", values, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempBetween(String value1, String value2) {
            addCriterion("bodytemp between", value1, value2, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andBodytempNotBetween(String value1, String value2) {
            addCriterion("bodytemp not between", value1, value2, "bodytemp");
            return (Criteria) this;
        }

        public Criteria andHumidityIsNull() {
            addCriterion("humidity is null");
            return (Criteria) this;
        }

        public Criteria andHumidityIsNotNull() {
            addCriterion("humidity is not null");
            return (Criteria) this;
        }

        public Criteria andHumidityEqualTo(String value) {
            addCriterion("humidity =", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotEqualTo(String value) {
            addCriterion("humidity <>", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityGreaterThan(String value) {
            addCriterion("humidity >", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityGreaterThanOrEqualTo(String value) {
            addCriterion("humidity >=", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityLessThan(String value) {
            addCriterion("humidity <", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityLessThanOrEqualTo(String value) {
            addCriterion("humidity <=", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityLike(String value) {
            addCriterion("humidity like", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotLike(String value) {
            addCriterion("humidity not like", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityIn(List<String> values) {
            addCriterion("humidity in", values, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotIn(List<String> values) {
            addCriterion("humidity not in", values, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityBetween(String value1, String value2) {
            addCriterion("humidity between", value1, value2, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotBetween(String value1, String value2) {
            addCriterion("humidity not between", value1, value2, "humidity");
            return (Criteria) this;
        }

        public Criteria andCrashIsNull() {
            addCriterion("crash is null");
            return (Criteria) this;
        }

        public Criteria andCrashIsNotNull() {
            addCriterion("crash is not null");
            return (Criteria) this;
        }

        public Criteria andCrashEqualTo(String value) {
            addCriterion("crash =", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashNotEqualTo(String value) {
            addCriterion("crash <>", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashGreaterThan(String value) {
            addCriterion("crash >", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashGreaterThanOrEqualTo(String value) {
            addCriterion("crash >=", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashLessThan(String value) {
            addCriterion("crash <", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashLessThanOrEqualTo(String value) {
            addCriterion("crash <=", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashLike(String value) {
            addCriterion("crash like", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashNotLike(String value) {
            addCriterion("crash not like", value, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashIn(List<String> values) {
            addCriterion("crash in", values, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashNotIn(List<String> values) {
            addCriterion("crash not in", values, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashBetween(String value1, String value2) {
            addCriterion("crash between", value1, value2, "crash");
            return (Criteria) this;
        }

        public Criteria andCrashNotBetween(String value1, String value2) {
            addCriterion("crash not between", value1, value2, "crash");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andQxygenIsNull() {
            addCriterion("qxygen is null");
            return (Criteria) this;
        }

        public Criteria andQxygenIsNotNull() {
            addCriterion("qxygen is not null");
            return (Criteria) this;
        }

        public Criteria andQxygenEqualTo(String value) {
            addCriterion("qxygen =", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenNotEqualTo(String value) {
            addCriterion("qxygen <>", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenGreaterThan(String value) {
            addCriterion("qxygen >", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenGreaterThanOrEqualTo(String value) {
            addCriterion("qxygen >=", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenLessThan(String value) {
            addCriterion("qxygen <", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenLessThanOrEqualTo(String value) {
            addCriterion("qxygen <=", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenLike(String value) {
            addCriterion("qxygen like", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenNotLike(String value) {
            addCriterion("qxygen not like", value, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenIn(List<String> values) {
            addCriterion("qxygen in", values, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenNotIn(List<String> values) {
            addCriterion("qxygen not in", values, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenBetween(String value1, String value2) {
            addCriterion("qxygen between", value1, value2, "qxygen");
            return (Criteria) this;
        }

        public Criteria andQxygenNotBetween(String value1, String value2) {
            addCriterion("qxygen not between", value1, value2, "qxygen");
            return (Criteria) this;
        }

        public Criteria andPositioningSIsNull() {
            addCriterion("positioning_s is null");
            return (Criteria) this;
        }

        public Criteria andPositioningSIsNotNull() {
            addCriterion("positioning_s is not null");
            return (Criteria) this;
        }

        public Criteria andPositioningSEqualTo(String value) {
            addCriterion("positioning_s =", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSNotEqualTo(String value) {
            addCriterion("positioning_s <>", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSGreaterThan(String value) {
            addCriterion("positioning_s >", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSGreaterThanOrEqualTo(String value) {
            addCriterion("positioning_s >=", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSLessThan(String value) {
            addCriterion("positioning_s <", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSLessThanOrEqualTo(String value) {
            addCriterion("positioning_s <=", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSLike(String value) {
            addCriterion("positioning_s like", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSNotLike(String value) {
            addCriterion("positioning_s not like", value, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSIn(List<String> values) {
            addCriterion("positioning_s in", values, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSNotIn(List<String> values) {
            addCriterion("positioning_s not in", values, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSBetween(String value1, String value2) {
            addCriterion("positioning_s between", value1, value2, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningSNotBetween(String value1, String value2) {
            addCriterion("positioning_s not between", value1, value2, "positioningS");
            return (Criteria) this;
        }

        public Criteria andPositioningDataIsNull() {
            addCriterion("positioning_data is null");
            return (Criteria) this;
        }

        public Criteria andPositioningDataIsNotNull() {
            addCriterion("positioning_data is not null");
            return (Criteria) this;
        }

        public Criteria andPositioningDataEqualTo(String value) {
            addCriterion("positioning_data =", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataNotEqualTo(String value) {
            addCriterion("positioning_data <>", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataGreaterThan(String value) {
            addCriterion("positioning_data >", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataGreaterThanOrEqualTo(String value) {
            addCriterion("positioning_data >=", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataLessThan(String value) {
            addCriterion("positioning_data <", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataLessThanOrEqualTo(String value) {
            addCriterion("positioning_data <=", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataLike(String value) {
            addCriterion("positioning_data like", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataNotLike(String value) {
            addCriterion("positioning_data not like", value, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataIn(List<String> values) {
            addCriterion("positioning_data in", values, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataNotIn(List<String> values) {
            addCriterion("positioning_data not in", values, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataBetween(String value1, String value2) {
            addCriterion("positioning_data between", value1, value2, "positioningData");
            return (Criteria) this;
        }

        public Criteria andPositioningDataNotBetween(String value1, String value2) {
            addCriterion("positioning_data not between", value1, value2, "positioningData");
            return (Criteria) this;
        }

        public Criteria andStepTimeIsNull() {
            addCriterion("step_time is null");
            return (Criteria) this;
        }

        public Criteria andStepTimeIsNotNull() {
            addCriterion("step_time is not null");
            return (Criteria) this;
        }

        public Criteria andStepTimeEqualTo(Integer value) {
            addCriterion("step_time =", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeNotEqualTo(Integer value) {
            addCriterion("step_time <>", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeGreaterThan(Integer value) {
            addCriterion("step_time >", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("step_time >=", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeLessThan(Integer value) {
            addCriterion("step_time <", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeLessThanOrEqualTo(Integer value) {
            addCriterion("step_time <=", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeIn(List<Integer> values) {
            addCriterion("step_time in", values, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeNotIn(List<Integer> values) {
            addCriterion("step_time not in", values, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeBetween(Integer value1, Integer value2) {
            addCriterion("step_time between", value1, value2, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("step_time not between", value1, value2, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepEachIsNull() {
            addCriterion("step_each is null");
            return (Criteria) this;
        }

        public Criteria andStepEachIsNotNull() {
            addCriterion("step_each is not null");
            return (Criteria) this;
        }

        public Criteria andStepEachEqualTo(Integer value) {
            addCriterion("step_each =", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachNotEqualTo(Integer value) {
            addCriterion("step_each <>", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachGreaterThan(Integer value) {
            addCriterion("step_each >", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachGreaterThanOrEqualTo(Integer value) {
            addCriterion("step_each >=", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachLessThan(Integer value) {
            addCriterion("step_each <", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachLessThanOrEqualTo(Integer value) {
            addCriterion("step_each <=", value, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachIn(List<Integer> values) {
            addCriterion("step_each in", values, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachNotIn(List<Integer> values) {
            addCriterion("step_each not in", values, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachBetween(Integer value1, Integer value2) {
            addCriterion("step_each between", value1, value2, "stepEach");
            return (Criteria) this;
        }

        public Criteria andStepEachNotBetween(Integer value1, Integer value2) {
            addCriterion("step_each not between", value1, value2, "stepEach");
            return (Criteria) this;
        }

        public Criteria andHrvIsNull() {
            addCriterion("hrv is null");
            return (Criteria) this;
        }

        public Criteria andHrvIsNotNull() {
            addCriterion("hrv is not null");
            return (Criteria) this;
        }

        public Criteria andHrvEqualTo(String value) {
            addCriterion("hrv =", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvNotEqualTo(String value) {
            addCriterion("hrv <>", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvGreaterThan(String value) {
            addCriterion("hrv >", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvGreaterThanOrEqualTo(String value) {
            addCriterion("hrv >=", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvLessThan(String value) {
            addCriterion("hrv <", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvLessThanOrEqualTo(String value) {
            addCriterion("hrv <=", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvLike(String value) {
            addCriterion("hrv like", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvNotLike(String value) {
            addCriterion("hrv not like", value, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvIn(List<String> values) {
            addCriterion("hrv in", values, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvNotIn(List<String> values) {
            addCriterion("hrv not in", values, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvBetween(String value1, String value2) {
            addCriterion("hrv between", value1, value2, "hrv");
            return (Criteria) this;
        }

        public Criteria andHrvNotBetween(String value1, String value2) {
            addCriterion("hrv not between", value1, value2, "hrv");
            return (Criteria) this;
        }

        public Criteria andMoodIsNull() {
            addCriterion("mood is null");
            return (Criteria) this;
        }

        public Criteria andMoodIsNotNull() {
            addCriterion("mood is not null");
            return (Criteria) this;
        }

        public Criteria andMoodEqualTo(String value) {
            addCriterion("mood =", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodNotEqualTo(String value) {
            addCriterion("mood <>", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodGreaterThan(String value) {
            addCriterion("mood >", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodGreaterThanOrEqualTo(String value) {
            addCriterion("mood >=", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodLessThan(String value) {
            addCriterion("mood <", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodLessThanOrEqualTo(String value) {
            addCriterion("mood <=", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodLike(String value) {
            addCriterion("mood like", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodNotLike(String value) {
            addCriterion("mood not like", value, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodIn(List<String> values) {
            addCriterion("mood in", values, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodNotIn(List<String> values) {
            addCriterion("mood not in", values, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodBetween(String value1, String value2) {
            addCriterion("mood between", value1, value2, "mood");
            return (Criteria) this;
        }

        public Criteria andMoodNotBetween(String value1, String value2) {
            addCriterion("mood not between", value1, value2, "mood");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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