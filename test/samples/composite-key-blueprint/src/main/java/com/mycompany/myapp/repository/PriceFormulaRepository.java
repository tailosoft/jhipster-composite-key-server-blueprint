package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PriceFormula;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PriceFormula entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriceFormulaRepository extends JpaRepository<PriceFormula, Integer>, JpaSpecificationExecutor<PriceFormula> {

}
