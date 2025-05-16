package controller.Game;

import model.App;
import model.Game;
import model.map.Refrigerator;
import model.Result;
import model.lives.Player;
import model.enums.ProduceQuality;
import model.items.*;
import model.items.crafting.Produce;
import model.items.crafting.ProducerArtisan;
import model.items.crafting.UnProducerArtisan;
import model.items.plants.Fruit;
import model.items.plants.Seed;
import model.items.tools.BackPack;


public class CommonGameController {
    public static Result exitGame() {
        App.setCurrentGame(null);
        return new Result(true, "exited game");
    }

    public static Result deleteGame() {
        return null;
    }

    // TODO add functions for "force terminate"

    public static Result passOut() {
        App.getCurrentGame().getCurrentPlayer().setEnergy(0);
        return new Result(true, "you passed out!" + nextTurn().message());
    }

    public static Result nextTurn() {
        Game game = App.getCurrentGame();
        game.nextTurn();
        String notification = getNotification();
        return new Result(true, String.format("it is %s's turn",
                game.getCurrentPlayer().getControllingUser().getUsername()) + notification);
    }

    private static String getNotification() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        StringBuilder messageBuilder = new StringBuilder();

        if (!player.getReceivedTrades().isEmpty()){
            messageBuilder.append("\nYou have some trade to do");
        }
        if (!player.getReceivedGifts().isEmpty()){
            messageBuilder.append("\nYou have some gift to open");
        }
        if (!(player.getReceivedRequests()).isEmpty()){
            messageBuilder.append("\nYou have some marriage proposal");
        }

        return messageBuilder.toString();
    }

    public static Result showTime() {
        Game game = App.getCurrentGame();
        return new Result(true, String.format("%d o'clock", game.getDateTime().getHour()));
    }

    public static Result showDate() {
        Game game = App.getCurrentGame();
        return new Result(true, String.format("%d/%s/%d",
                game.getDateTime().getYear(), game.getDateTime().getSeason().toString().toLowerCase(), game.getDateTime().getDay()));
    }

    public static Result showDateTime() {
        return new Result(true, showDate().message() + ' ' + showTime().message());

    }

    public static Result showDayOfWeek() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getDateTime().getWeekDay().toString().toLowerCase());
    }

    public static Result showSeason() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getDateTime().getSeason().toString().toLowerCase());
    }

    public static Result showWeather() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getCurrentWeather().toString().toLowerCase());
    }

    public static Result showWeatherForecast() {
        Game game = App.getCurrentGame();
        return new Result(true, game.getTomorrowWeather().toString().toLowerCase());
    }

    public static Result showEnergy() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        return new Result(true, String.format("you have %d energy left.", player.getEnergy()));
    }


    public static Item findItem(String ItemName){

        Seed seed = Seed.getSeed(ItemName);
        if(seed != null){
            return seed;
        }

        Fruit fruit = Fruit.getFruit(ItemName);
        if(fruit != null){
            return fruit;
        }

//        Artisan artisan = Artisan.getArtisan(ItemName);
//        if(artisan != null){
//            return artisan;
//        }

        ProducerArtisan producerArtisan = ProducerArtisan.getProducerArtisan(ItemName);
        if(producerArtisan != null){
            return producerArtisan;
        }

        UnProducerArtisan unProducerArtisan = UnProducerArtisan.getUnProducerArtisan(ItemName);
        if(unProducerArtisan != null){
            return unProducerArtisan;
        }

        Fish fish = Fish.getFish(ItemName);
        if(fish != null){
            return fish;
        }

        Food food = Food.getFood(ItemName);
        if(food != null){
            return food;
        }

        Material material = Material.getMaterial(ItemName);
        if(material != null){
            return material;
        }

        Produce produce = Produce.getProduce(ItemName);
        if(produce != null){
            return produce;
        }

        AnimalProduce animalProduce = AnimalProduce.getAnimalProduce(ItemName);
        if(animalProduce != null){
            return animalProduce;
        }

        Fertilize fertilize = Fertilize.getFertilizer(ItemName);
        if(fertilize != null){
            return fertilize;
        }



        return null;
    }

    public static int numberOfItemInBackPack(String ItemName){
        Player player = App.getCurrentGame().getCurrentPlayer();
        Integer number = 0;
        if(ItemName.equals("fish")){
            for(Fish fish : Fish.getFishesValues()){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fish.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().getOrDefault(fish,0);
                    number += player.getRefrigerator().getNumberOfItemInRefrigerator().getOrDefault(fish,0);
                }
            }
        }
        else if(ItemName.equals("Cheese") || ItemName.equals("Goat Cheese") || ItemName.equals("Mayonnaise") ){
            number += player.getBackpack().getNumberOfItemInBackPack().
                    getOrDefault(Produce.getProduce(ItemName),0);
            number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                    getOrDefault(Produce.getProduce(ItemName),0);
            number += player.getBackpack().getNumberOfItemInBackPack().
                    getOrDefault(Produce.getProduce("Large " +ItemName),0);
            number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                    getOrDefault(Produce.getProduce("Large " +ItemName),0);
        }
        else if(ItemName.equals("Oil")){
            number += player.getBackpack().getNumberOfItemInBackPack().
                    getOrDefault(Produce.getProduce("Corn " + ItemName),0);
            number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                    getOrDefault(Produce.getProduce("Corn " + ItemName),0);
            number += player.getBackpack().getNumberOfItemInBackPack().
                    getOrDefault(Produce.getProduce("Sunflower Seed " + ItemName),0);
            number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                    getOrDefault(Produce.getProduce("Sunflower Seed " + ItemName),0);
            number += player.getBackpack().getNumberOfItemInBackPack().
                    getOrDefault(Produce.getProduce("Sunflower " + ItemName),0);
            number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                    getOrDefault( Produce.getProduce("Sunflower " + ItemName),0);
        }
        else {
            Item item = findItem(ItemName);
            if(item == null){
                return 0;
            }
            else if(item instanceof Fish fish){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fish.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().getOrDefault(fish,0);
                    number += player.getRefrigerator().getNumberOfItemInRefrigerator().getOrDefault(fish,0);
                }
            }
            else if(item instanceof Fruit fruit){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fruit.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().getOrDefault(fruit,0);
                    number += player.getRefrigerator().getNumberOfItemInRefrigerator().getOrDefault(fruit,0);
                }
            }
            else if(item instanceof AnimalProduce animalProduce){
                for(ProduceQuality quality : ProduceQuality.values()){
                    animalProduce.setQuality(quality);
                    number += player.getBackpack().getNumberOfItemInBackPack().getOrDefault(animalProduce,0);
                    number += player.getRefrigerator().getNumberOfItemInRefrigerator().
                            getOrDefault(animalProduce,0);
                }
            }
            else {
                number = player.getBackpack().getNumberOfItemInBackPack().getOrDefault(item,0);
                number += player.getRefrigerator().getNumberOfItemInRefrigerator().getOrDefault(item,0);
            }

        }

        return number;

    }

    public static void removeItemFromBackPack(String ItemName, int number){
        Player player = App.getCurrentGame().getCurrentPlayer();
        BackPack backPack = player.getBackpack();
        Refrigerator refrigerator = player.getRefrigerator();

        int amount ;
        if(ItemName.equals("fish")){
            boolean isDone = false;
            for(Fish fish : Fish.getFishesValues()){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fish.setQuality(quality);

                    amount = backPack.getNumberOfItemInBackPack().getOrDefault(fish,0);
                    if(amount <= number){
                        backPack.removeItem(fish,amount);
                        number -= amount;
                    }
                    else {
                        backPack.removeItem(fish,number);
                        number = 0;
                    }
                    if(number == 0){
                        isDone = true;
                        break;
                    }

                    amount = refrigerator.getNumberOfItemInRefrigerator().getOrDefault(fish,0);
                    if(amount <= number){
                        refrigerator.removeItem(fish,amount);
                        number -= amount;
                    }
                    else {
                        refrigerator.removeItem(fish,number);
                        number = 0;
                    }
                    if(number == 0){
                        isDone = true;
                        break;
                    }
                }
                if(isDone){
                    break;
                }
            }
        }
        else if(ItemName.equals("Cheese") || ItemName.equals("Goat Cheese") || ItemName.equals("Mayonnaise")){
            Produce produce = Produce.getProduce(ItemName);
            amount = backPack.getNumberOfItemInBackPack().getOrDefault(produce,0);
            amount += refrigerator.getNumberOfItemInRefrigerator().getOrDefault(produce,0);

            if(number > amount){
                removeItemFromInventory(produce,amount);
                number -= amount;
                removeItemFromInventory(Produce.getProduce("Large " + ItemName),number);
            }
            else {
                removeItemFromInventory(produce,number);
            }
        }
        else if(ItemName.equals("Oil")){
            Produce produce = Produce.getProduce("Corn " + ItemName);
            amount = backPack.getNumberOfItemInBackPack().getOrDefault(produce,0);
            amount += refrigerator.getNumberOfItemInRefrigerator().getOrDefault(produce,0);
            if(number > amount){
                removeItemFromInventory(produce,amount);
                number -= amount;
                produce = Produce.getProduce("Sunflower Seed " + ItemName);
                amount = backPack.getNumberOfItemInBackPack().getOrDefault(produce,0);
                amount += refrigerator.getNumberOfItemInRefrigerator().getOrDefault(produce,0);
                if(number > amount){
                    removeItemFromInventory(produce,amount);
                    number -= amount;
                    removeItemFromInventory(Produce.getProduce("Sunflower " + ItemName),number);
                }
                else {
                    removeItemFromInventory(produce,number);
                }
            }
            else{
                removeItemFromInventory(produce,number);
            }
        }
        else {
            Item item = findItem(ItemName);
            if(item instanceof Fish fish){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fish.setQuality(quality);
                    amount = backPack.getNumberOfItemInBackPack().getOrDefault(fish,0);
                    if(amount <= number){
                        backPack.removeItem(fish,amount);
                        number -= amount;
                    }
                    else {
                        backPack.removeItem(fish,number);
                        number = 0;
                    }

                    if(number == 0){
                        break;
                    }

                    amount = refrigerator.getNumberOfItemInRefrigerator().getOrDefault(fish,0);
                    if(amount <= number){
                        refrigerator.removeItem(fish,amount);
                        number -= amount;
                    }
                    else {
                        refrigerator.removeItem(fish,number);
                        number = 0;
                    }

                    if(number == 0){
                        break;
                    }
                }
            }
            else if(item instanceof Fruit fruit){
                for(ProduceQuality quality : ProduceQuality.values()){
                    fruit.setQuality(quality);
                    amount = backPack.getNumberOfItemInBackPack().getOrDefault(fruit,0);
                    if(amount <= number){
                        backPack.removeItem(fruit,amount);
                        number -= amount;
                    }
                    else{
                        backPack.removeItem(fruit,number);
                        number = 0;
                    }

                    if(number == 0){
                        break;
                    }

                    amount = refrigerator.getNumberOfItemInRefrigerator().getOrDefault(fruit,0);
                    if(amount <= number){
                        refrigerator.removeItem(fruit,amount);
                        number -= amount;
                    }
                    else {
                        refrigerator.removeItem(fruit,number);
                        number = 0;
                    }

                    if(number == 0){
                        break;
                    }
                }
            }
            else if(item instanceof AnimalProduce animalProduce){
                for(ProduceQuality quality : ProduceQuality.values()){
                    animalProduce.setQuality(quality);
                    amount = backPack.getNumberOfItemInBackPack().getOrDefault(animalProduce,0);
                    if(amount <= number){
                        backPack.removeItem(animalProduce,amount);
                        number -= amount;
                    }
                    else {
                        backPack.removeItem(animalProduce,number);
                        number = 0;
                    }

                    if(number == 0){
                        break;
                    }

                    amount = refrigerator.getNumberOfItemInRefrigerator().getOrDefault(animalProduce,0);
                    if (amount <= number){
                        refrigerator.removeItem(animalProduce,amount);
                        number -= amount;
                    }
                    else {
                        refrigerator.removeItem(animalProduce,number);
                        number = 0;
                    }

                    if(number == 0){
                        break;
                    }
                }
            }
            else{
                amount = backPack.getNumberOfItemInBackPack().getOrDefault(item,0);
                if(amount <= number){
                    backPack.removeItem(item,amount);
                    number -= amount;
                    refrigerator.removeItem(item,number);
                }
                backPack.removeItem(item,number);
            }
        }
    }

    public static boolean removeItemFromInventory(Item item, int number){

        Player player = App.getCurrentGame().getCurrentPlayer();
        int amount = player.getBackpack().getNumberOfItemInBackPack().getOrDefault(item,0);
        if(number - amount > player.getRefrigerator().getNumberOfItemInRefrigerator().getOrDefault(item,0)){
            return false;
        }
        else if(amount < number){
            player.getBackpack().removeItem(item,amount);
            number -= amount;
            player.getRefrigerator().removeItem(item,number);
        }
        player.getBackpack().removeItem(item,number);
        return true;

    }

    public static ProduceQuality giveQuality(double quality){
        if(quality >= 0 && quality < 0.5){
            return ProduceQuality.NORMAL;
        }
        else if(quality >= 0.5 && quality < 0.7){
            return ProduceQuality.SILVER;
        }
        else if(quality >= 0.7 && quality < 0.9){
            return ProduceQuality.GOLD;
        }
        else{
            return ProduceQuality.IRIDIUM;
        }
    }
    public static void getregectedInMarriage(Player player){
        player.setHeartBroken(7);
    }
    public static void acceptMarriage(Player player){
        //TODO zaminashono ok kon @korosh
    }
    //TODO check baghal ham. ham bra player ham bra satl
    public static Result sell(String product, int count) {
        //next to each other
        Fish fish = Fish.getFish(product);
        if (fish != null) {
            if (count != 1) {
                if (removeItemFromInventory(fish,count) == false){
                    return new Result(false,"not enough products");
                }
            }
            else if(App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(fish) == 0){
                return new Result(false,"not enough products");
            }
            else{
                int money = 0;
                if(count == -1){
                     money = (int)(fish.getBaseSellPrice() * fish.getQuality().getValue())
                            * App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(fish);
                }
                else{
                     money = (int)(fish.getBaseSellPrice() * fish.getQuality().getValue()) * count;
                }
                App.getCurrentGame().getCurrentPlayer().increaseNextDayMoney(money);
                return new Result(true,"item sold successfully");
            }
        }
        Fruit fruit = Fruit.getFruit(product);
        if (fruit != null) {
            if (count != 1) {
                if (removeItemFromInventory(fruit,count) == false){
                    return new Result(false,"not enough products");
                }
            }
            else if(App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(fruit) == 0){
                return new Result(false,"not enough products");
            }
            else{
                int money = 0;
                if(count == -1){
                    money = (int)(fruit.getBaseSellPrice() * fruit.getQuality().getValue())
                            * App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(fruit);
                }
                else{
                    money = (int)(fish.getBaseSellPrice() * fruit.getQuality().getValue()) * count;
                }
                App.getCurrentGame().getCurrentPlayer().increaseNextDayMoney(money);
                return new Result(true,"item sold successfully");
            }
        }

        //Artisan artisan =
        Food food = Food.getFood(product);
        if (food != null) {
            if (count != 1) {
                if (removeItemFromInventory(food,count) == false){
                    return new Result(false,"not enough products");
                }
            }
            else if(App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(food) == 0){
                return new Result(false,"not enough products");
            }
            else{
                int money = 0;
                if(count == -1){
                    money = food.getBaseSellPrice() * App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(food);
                }
                else{
                    money = food.getBaseSellPrice() * count;
                }
                App.getCurrentGame().getCurrentPlayer().increaseNextDayMoney(money);
                return new Result(true,"item sold successfully");
            }
        }
        Produce produce = Produce.getProduce(product);
        if (produce != null) {
            if (count != 1) {
                if (removeItemFromInventory(produce,count) == false){
                    return new Result(false,"not enough products");
                }
            }
            else if(App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(produce) == 0){
                return new Result(false,"not enough products");
            }
            else{
                int money = 0;
                if(count == -1){
                    money = produce.getBaseSellPrice() * App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(produce);
                }
                else{
                    money = produce.getBaseSellPrice() * count;
                }
                App.getCurrentGame().getCurrentPlayer().increaseNextDayMoney(money);
                return new Result(true,"item sold successfully");
            }
        }
        UnProducerArtisan unproduce = UnProducerArtisan.getUnProducerArtisan(product);
        if (unproduce != null) {
            if (count != 1) {
                if (removeItemFromInventory(unproduce,count) == false){
                    return new Result(false,"not enough products");
                }
            }
            else if(App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(unproduce) == 0){
                return new Result(false,"not enough products");
            }
            else{

                int money = 0;
                if(count == -1){
                    money = unproduce.getBaseSellPrice() * App.getCurrentGame().getCurrentPlayer().getBackpack().getNumberOfItemInBackPack().get(unproduce);
                }
                else{
                    money = unproduce.getBaseSellPrice() * count;
                }
                App.getCurrentGame().getCurrentPlayer().increaseNextDayMoney(money);
                return new Result(false,"item sold successfully");
            }
        }
        return new Result(false,"you can't sell this product");
    }
    public static void nextDayMoney() {
        for(Player player : App.getCurrentGame().getPlayers()){
            player.increaseMoney(player.getNextDayMoney());
            player.setNextDayMoney(0);
        }
    }
}
