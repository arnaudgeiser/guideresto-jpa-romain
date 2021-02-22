package ch.hearc.ig.guideresto.business;

public class Grade {

    private Integer id;
    private Integer grade;
    private CompleteEvaluation evaluation;
    private EvaluationCriteria criteria;

    public Grade(Integer id, Integer grade, CompleteEvaluation evaluation, EvaluationCriteria criteria) {
        this.id = id;
        this.grade = grade;
        this.evaluation = evaluation;
        this.criteria = criteria;
    }

    public Integer getGrade() {
        return grade;
    }

    public EvaluationCriteria getCriteria() {
        return criteria;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvaluation(CompleteEvaluation completeEvaluation) {
        this.evaluation = completeEvaluation;
    }
}