package ch.hearc.ig.guideresto.service;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantOverview;
import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.DBTransaction;
import utils.RestaurantToRestaurantOverview;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantService {

  private final DBTransaction dbTransaction;

  public RestaurantService(DBTransaction dbTransaction) {
    this.dbTransaction = dbTransaction;
  }

  public Set<RestaurantOverview> researchAllRestaurants() {
    TypedQuery<Restaurant> query = dbTransaction.getEm()
            .createNamedQuery("researchAllRestaurants", Restaurant.class);
    List<Restaurant> restaurants = query.getResultList();
    Set<RestaurantOverview> restaurantOverviews = new HashSet<>();
    for (Restaurant restaurant : restaurants) {
      RestaurantOverview restaurantOverview = RestaurantToRestaurantOverview.convert(restaurant);
      restaurantOverviews.add(restaurantOverview);
    }
    return restaurantOverviews;
  }

  public Restaurant researchRestaurantById(int restaurantId) {
    TypedQuery<Restaurant> query = dbTransaction.getEm()
            .createNamedQuery("researchRestaurantById", Restaurant.class)
            .setParameter(1, restaurantId);
    return query.getSingleResult();
  }

  public Set<Restaurant> researchRestaurantsByName(String research) {
    TypedQuery<Restaurant> query = dbTransaction.getEm()
            .createNamedQuery("researchRestaurantsByName", Restaurant.class)
            .setParameter(1, research + "%");
    return query.getResultStream().collect(Collectors.toSet());
  }

  public Set<Restaurant> researchRestaurantsByCityName(String research) {
    TypedQuery<Restaurant> query = dbTransaction.getEm()
            .createNamedQuery("researchRestaurantsByCityName", Restaurant.class)
            .setParameter(1, research + "%");
    return query.getResultStream().collect(Collectors.toSet());
  }

  public Set<Restaurant> researchRestaurantsByType(RestaurantType restaurantType) {
    TypedQuery<Restaurant> query = dbTransaction.getEm()
            .createNamedQuery("researchRestaurantsByType", Restaurant.class)
            .setParameter(1, restaurantType);
    return query.getResultStream().collect(Collectors.toSet());
  }

  public Set<RestaurantType> researchAllRestaurantTypes() {
    TypedQuery<RestaurantType> query = dbTransaction.getEm()
            .createNamedQuery("researchAllRestaurantTypes", RestaurantType.class);
    return query.getResultStream().collect(Collectors.toSet());
  }

  public Set<City> researchAllCities() {
    TypedQuery<City> query = dbTransaction.getEm()
            .createNamedQuery("researchAllCities", City.class);
    return query.getResultStream().collect(Collectors.toSet());
  }

  public void insertRestaurant(Restaurant restaurant) {
    dbTransaction.getEm().persist(restaurant);
    dbTransaction.getEm().getTransaction().commit();
  }

  public void insertCity(City city) {
    dbTransaction.getEm().persist(city);
    dbTransaction.getEm().getTransaction().commit();
  }

  public void insertBasicEvaluation(BasicEvaluation eval) {
    dbTransaction.getEm().persist(eval);
    dbTransaction.getEm().getTransaction().commit();
  }

  public Set<EvaluationCriteria> researchAllEvaluationCriteria() {
    TypedQuery<EvaluationCriteria> query = dbTransaction.getEm()
            .createNamedQuery("researchAllEvaluationCriteria", EvaluationCriteria.class);
    return query.getResultStream().collect(Collectors.toSet());
  }

  public void insertCompleteEvaluation(CompleteEvaluation eval) {
    dbTransaction.getEm().persist(eval);
    dbTransaction.getEm().getTransaction().commit();
  }

  public void updateRestaurant(Restaurant restaurant) {
    dbTransaction.getEm().persist(restaurant);
    dbTransaction.getEm().getTransaction().commit();
  }

  public void deleteRestaurant(Restaurant restaurant) {
    dbTransaction.getEm().remove(restaurant);
    dbTransaction.getEm().getTransaction().commit();
  }
}
