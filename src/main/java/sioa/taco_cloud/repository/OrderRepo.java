package sioa.taco_cloud.repository;

import sioa.taco_cloud.model.TacoOrder;

public interface OrderRepo {
    TacoOrder save(TacoOrder order);
}
