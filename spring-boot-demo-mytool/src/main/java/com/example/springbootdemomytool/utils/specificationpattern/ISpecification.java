package com.example.springbootdemomytool.utils.specificationpattern;

public interface ISpecification {

    /**
     * 候选者是否满足需求
     *
     * @param candidate
     * @return
     */
    boolean isSatisfiedBy(Object candidate);

    /**
     *
     * @param spec
     * @return
     */
    ISpecification and(ISpecification spec);



}
