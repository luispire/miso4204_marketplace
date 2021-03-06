/* ========================================================================
 * Copyright 2014 miso4204
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 miso4204

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.qualifier

*/

package co.edu.uniandes.csw.miso4204.creditcard.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.edu.uniandes.csw.miso4204.creditcard.logic.dto.CreditCardDTO;
import co.edu.uniandes.csw.miso4204.creditcard.logic.dto.CreditCardPageDTO;
import co.edu.uniandes.csw.miso4204.creditcard.persistence.converter.CreditCardConverter;
import co.edu.uniandes.csw.miso4204.creditcard.persistence.entity.CreditCardEntity;

public abstract class _CreditCardPersistence{

  	protected EntityManager entityManager;
	
	public CreditCardDTO createCreditCard(CreditCardDTO creditCard) {
		CreditCardEntity entity=CreditCardConverter.persistenceDTO2Entity(creditCard);
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return CreditCardConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<CreditCardDTO> getCreditCards() {
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery("select u from CreditCardEntity u");
		List<CreditCardDTO> result = CreditCardConverter.entity2PersistenceDTOList(q.getResultList());
		entityManager.getTransaction().commit();
		return result;
		
	}

	@SuppressWarnings("unchecked")
	public CreditCardPageDTO getCreditCards(Integer page, Integer maxRecords) {
		entityManager.getTransaction().begin();
		Query count = entityManager.createQuery("select count(u) from CreditCardEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from CreditCardEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		CreditCardPageDTO response = new CreditCardPageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(CreditCardConverter.entity2PersistenceDTOList(q.getResultList()));
		entityManager.getTransaction().commit();
		return response;
	}

	public CreditCardDTO getCreditCard(Long id) {
		entityManager.getTransaction().begin();
		CreditCardDTO result = CreditCardConverter.entity2PersistenceDTO(entityManager.find(CreditCardEntity.class, id));
		entityManager.getTransaction().commit();
		return result;
	}

	public void deleteCreditCard(Long id) {
		entityManager.getTransaction().begin();
		CreditCardEntity entity=entityManager.find(CreditCardEntity.class, id);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	public void updateCreditCard(CreditCardDTO detail) {
		entityManager.getTransaction().begin();
		CreditCardEntity entity=entityManager.merge(CreditCardConverter.persistenceDTO2Entity(detail));
		CreditCardConverter.entity2PersistenceDTO(entity);
		entityManager.getTransaction().commit();
	}

}