// Примеры фабричных методов
abstract class AddOn {
  type: string;
}

abstract class Cake {
  type: string;

  abstract createAddOn(addOnType: string): AddOn;
}

////////////////////////////////// 1 //////////////////////////////////
class ComplexCake implements Cake {
  public type = 'COMPLEX';

  public createAddOn(addOnType: string): AddOn {
    switch (addOnType) {
      case 'PHOTO_ON_CAKE':
        return new PhotoOnCake();
      case 'TEXT_ON_CAKE':
        return new TextOnCake();
      case 'DEPOSIT':
        return new Deposit();
    }
  }
}

////////////////////////////////// 2 //////////////////////////////////
class PiecesCake implements Cake {
  public type = 'PIECES';

  public createAddOn(addOnType: string): AddOn {
    switch (addOnType) {
      case 'CAKE_FILLING':
        return new CakeFilling();
      case 'EXTRA_PRODUCT':
        return new ExtraProduct();
      case 'GREETING_CARD':
        return new GreetingCard();
    }
  }
}

////////////////////////////////// 3 //////////////////////////////////
class CakeBuilder {
  public createCake(type: string): Cake {
    switch (type) {
      case 'COMPLEX':
        return new ComplexCake();
      case 'PIECES':
        return new PiecesCake();
    }
  }
}

// Правильное именование интерфейсов и абстрактных классов
////////////////////////////////// 1 //////////////////////////////////
export class OrderDetails {
  id: number;
  price: number;
  orderStatus: string;
  paymentMethod: string;
  number: string;
  createdDate: Date;
  //...
}

////////////////////////////////// 2 //////////////////////////////////
abstract class AbstractControl {
  validator: ValidatorFn | null;
  asyncValidator: AsyncValidatorFn | null;
  // ...
}

////////////////////////////////// 3 //////////////////////////////////
abstract class AnalyticHandler {
  abstract handle(data: AnalyticEventData): void;
}
