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

package co.edu.uniandes.csw.miso4204.shoppingcart.master.persistence;
import co.edu.uniandes.csw.miso4204.shoppingcartitem.logic.dto.ShoppingCartItemDTO;
import co.edu.uniandes.csw.miso4204.shoppingcart.master.persistence.entity.ShoppingCartshoppingCartItemEntity;
import co.edu.uniandes.csw.miso4204.shoppingcartitem.persistence.converter.ShoppingCartItemConverter;
import co.edu.uniandes.csw.miso4204.shoppingcart.logic.dto.ShoppingCartDTO;
import co.edu.uniandes.csw.miso4204.shoppingcart.master.logic.dto.ShoppingCartMasterDTO;
import co.edu.uniandes.csw.miso4204.shoppingcart.persistence.ShoppingCartPersistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class _ShoppingCartMasterPersistence{

    protected EntityManager entityManager;
    
    protected ShoppingCartPersistence shoppingcartPersistence;  

    public ShoppingCartMasterDTO getShoppingCart(Long shoppingcartId) {
        ShoppingCartMasterDTO shoppingcartMasterDTO = new ShoppingCartMasterDTO();
        ShoppingCartDTO shoppingcart = shoppingcartPersistence.getShoppingCart(shoppingcartId);
        shoppingcartMasterDTO.setShoppingCartEntity(shoppingcart);
        shoppingcartMasterDTO.setListshoppingCartItem(getShoppingCartshoppingCartItemEntityList(shoppingcartId));
        return shoppingcartMasterDTO;
    }

    public ShoppingCartshoppingCartItemEntity createShoppingCartshoppingCartItemEntity(ShoppingCartshoppingCartItemEntity entity) {
        entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
        return entity;
    }

   public void deleteShoppingCartshoppingCartItemEntity(Long shoppingCartId, Long shoppingCartItemId) {
		entityManager.getTransaction().begin();
        Query q = entityManager.createNamedQuery("ShoppingCartshoppingCartItemEntity.deleteShoppingCartshoppingCartItemEntity");
        q.setParameter("shoppingCartId", shoppingCartId);
        q.setParameter("shoppingCartItemId", shoppingCartItemId);
        q.executeUpdate();
		entityManager.getTransaction().commit();
    }

   public List<ShoppingCartItemDTO> getShoppingCartshoppingCartItemEntityList(Long shoppingCartId) {
		entityManager.getTransaction().begin();
		ArrayList<ShoppingCartItemDTO> resp = new ArrayList<ShoppingCartItemDTO>();
        Query q = entityManager.createNamedQuery("ShoppingCartshoppingCartItemEntity.getByMasterId");
        q.setParameter("shoppingCartId",shoppingCartId);
        List<ShoppingCartshoppingCartItemEntity> qResult =  q.getResultList();
        for (ShoppingCartshoppingCartItemEntity entity : qResult) { 
            if(entity.getShoppingCartItemIdEntity()==null){
                entityManager.refresh(entity);
            }
            resp.add(ShoppingCartItemConverter.entity2PersistenceDTO(entity.getShoppingCartItemIdEntity()));
        }
        entityManager.getTransaction().commit();
        return resp;
      
    }
 
}