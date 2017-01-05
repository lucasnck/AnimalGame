/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AnimalDAO;
import dao.AttributeDAO;
import dao.TypeDAO;
import entity.Animal;
import entity.Attribute;
import entity.Type;
import java.util.ArrayList;
import java.util.List;
import main.QuestionAnimal;
import main.QuestionAnimalAttribute;
import main.QuestionAnimalName;
import main.QuestionAttribute;

/**
 *
 * @author lucas
 *
 * BaseBean is created for verify the basic datas for run the game and keep the
 * information during the runtime
 *
 */
public class BaseBean {

    /**
     * Where animals live
     */
    private List<Type> types;

    /**
     * Animals of the game
     */
    private List<Animal> animals;

    /**
     * Animals characteristics
     */
    private List<Attribute> attributes;

    /**
     * Selected type of the animals at running
     */
    private Type selectedType;

    /**
     * Selected animals at running
     */
    private List<Animal> selectedAnimals;

    /**
     * Selected attributes of the animals at running
     */
    private List<Attribute> selectedAttributes;

    /**
     * constructor
     */
    public BaseBean() {
    }

    /**
     * This question open the QuestionAnimal.java JFrame
     *
     * QuestionAnimal ask to player if the animal that they think is the animal
     * in parameter
     *
     * @param animal
     */
    public void openQuestionAnimal(Animal animal) {
        //one animal in the list, ask to the user if the animal is the animal that they think
        QuestionAnimal questionAnimal = new QuestionAnimal(this);
        questionAnimal.setSelectedAnimal(animal);
        questionAnimal.setVisible(true);
    }

    /**
     * This question open the QuestionAttribute.java JFrame
     *
     * QuestionAttribute ask to player if the animal that they think have the
     * characteristic in the parameter
     *
     * @param attribute
     * @return
     */
    public QuestionAttribute openQuestionAttribute(Attribute attribute) {
        QuestionAttribute questionAttribute = new QuestionAttribute(this);
        questionAttribute.setSelectedLastAttribute(attribute);
        questionAttribute.showQuestion();
        return questionAttribute;
    }

    /**
     * This question open the QuestionAnimalName.java JFrame
     *
     * QuestionAnimalName ask to player the name of the animal that they think
     *
     * @param animal
     * @return
     */
    public QuestionAnimalName openQuestionAnimalName(Animal animal) {
        QuestionAnimalName questionAnimalName = new QuestionAnimalName(this);
        questionAnimalName.setAnotherAnimal(animal);
        questionAnimalName.setVisible(true);
        return questionAnimalName;
    }

    /**
     * This question open the QuestionAnimalAttribute.java JFrame
     *
     * QuestionAnimalAttribute ask to player the name of the attribute of the animal that they think
     * 
     * @param animal
     * @param anotherAnimal
     * @return 
     */
    public QuestionAnimalAttribute openQuestionAnimalAttributeName(Animal animal, Animal anotherAnimal) {
        QuestionAnimalAttribute questionAnimalAttribute = new QuestionAnimalAttribute(this);
        questionAnimalAttribute.setAnimal(animal);
        questionAnimalAttribute.setAnotherAnimal(anotherAnimal);
        questionAnimalAttribute.showQuestion();
        questionAnimalAttribute.setVisible(true);
        return questionAnimalAttribute;
    }

    /**
     * Check all database data
     */
    public void prepare() {
        checkTypes();
        checkAnimals();
        checkAttributes();
    }

    /**
     * check attributes and load for the runtime
     */
    private void checkAttributes() {
        AttributeDAO dao = new AttributeDAO();
        if (dao.list() != null) {
            loadAttributes();
        }
    }

    /**
     * load attributes
     */
    public void loadAttributes() {
        AttributeDAO dao = new AttributeDAO();
        attributes = dao.list();
        if (attributes == null || attributes.isEmpty()) {
            attributes = new ArrayList<>();
        }
    }

    private void checkAnimals() {
        AnimalDAO animalDAO = new AnimalDAO();
        if (animalDAO.list() == null || animalDAO.list().isEmpty()) {
            prepareAnimals();
        } else {
            loadAnimals();
        }
    }

    /**
     * Create the basic animals for the game
     */
    private void prepareAnimals() {
        animals = new ArrayList<>();
        Type water = new TypeDAO().getByName("àgua");
        Type ground = new TypeDAO().getByName("terra");
        animals.add(new Animal("Tubarão", water.getId()));
        animals.add(new Animal("Macaco", ground.getId()));
        AnimalDAO animalDAO = new AnimalDAO();
        animalDAO.save(animals);
        loadAnimals();
    }

    private void loadAnimals() {
        AnimalDAO animalDAO = new AnimalDAO();
        animals = animalDAO.list();
    }

    private void checkTypes() {
        TypeDAO typeDAO = new TypeDAO();
        if (typeDAO.list() == null || typeDAO.list().isEmpty()) {
            prepareTypes();
        } else {
            loadTypes();
        }
    }

    /**
     * Create the basic types for the game
     */
    private void prepareTypes() {
        TypeDAO typeDAO = new TypeDAO();
        types = new ArrayList<>();
        types.add(new entity.Type("àgua"));
        types.add(new entity.Type("terra"));
        typeDAO.save(types);
        loadTypes();
    }

    private void loadTypes() {
        TypeDAO typeDAO = new TypeDAO();
        types = typeDAO.list();
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public Type getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(Type selectedType) {
        this.selectedType = selectedType;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Animal> getSelectedAnimals() {
        return selectedAnimals;
    }

    public void setSelectedAnimals(List<Animal> selectedAnimals) {
        this.selectedAnimals = selectedAnimals;
    }

    public List<Attribute> getSelectedAttributes() {
        return selectedAttributes;
    }

    public void setSelectedAttributes(List<Attribute> selectedAttributes) {
        this.selectedAttributes = selectedAttributes;
    }

}
