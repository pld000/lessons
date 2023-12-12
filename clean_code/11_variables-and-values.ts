////////////////////////////////// 1 //////////////////////////////////
/**
 *  Сделал объявление переменной явным, поменял модификатор let на const,
 * */
function getProductTypeBuildingInfo() {
  // ...
  const upsellForProductType: ProductTypeUpsellProduct[] = this.getUpsellForProductType();
  // ...
}

////////////////////////////////// 2 //////////////////////////////////
/**
 * Инициализировал поля в классе
 * */
export class UsersOverview {
  public users: IUser[] = [];
  public usersCountTotal: number = 0;
  public filterQuery: string = '';
  public subscribedOnNews = '';
  public isPrivate: boolean = false;
  // ...
}

////////////////////////////////// 3 //////////////////////////////////
/**
 * Сделал явным объявление переменной upsellProducts
 * */
async function initUpsellProducts() {
  // ...
  const { results: upsellProducts }: IPaginatedResponse<IProductTypeUpsellProduct> = await this._productsApi
    .getUpsellProducts(this.bakeryId, this.productTypeId);
  this.upsellProducts = upsellProducts.map(p => ({ ...p, isCheck: this.selectedUpsellIds.indexOf(p.id) >= 0 }));
  // ...
}

////////////////////////////////// 4 //////////////////////////////////
/**
 *
 * */
  private async _init() {
    const { companyId, userId } = this._route.snapshot.params;
    this.company = await this._companyApi.getCompany(companyId).toPromise();
    this.user = await this._usersCustomersApi.getUserById(userId).toPromise();
    // const invoiceInfo: IUserInvoiceInfo = await this._usersCustomersApi.getUserInvoiceInfo(userId).toPromise();
    // const deliveryAddress = await this._usersCustomersApi.getDeliveryAddress(userId).toPromise();
    //
    // const patch = this._getUserFormPatch({ user: this.user, invoiceInfo, deliveryAddress});

    this.userForm = this._getUserForm(this.bakeryId);

    await this._loadUserOrders();
  }

////////////////////////////////// 5 //////////////////////////////////
/**
 * Добавил предупреждение для недопустимого значения поля
 * */
function isDeliveryFinished(cart: Cart): boolean {
  const { delivery: { orderId, outletId, timeRange, date, type, postNumber, deliveryAgentId } } = cart;

  const isBasicDeliveryInfoSet = Boolean(orderId) && Boolean(timeRange) && Boolean(date);

  if (type === DELIVERY_OPTIONS.PICKUP) {
    return isBasicDeliveryInfoSet && Boolean(outletId);
  }

  if (type === DELIVERY_OPTIONS.DELIVERY) {
    return isBasicDeliveryInfoSet && Boolean(postNumber) && Boolean(deliveryAgentId);
  }

  throw `delivery.type === ${type} is not supported`;
}

////////////////////////////////// 6 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 7 //////////////////////////////////
/**
 * Добавил начальную инициализацию полей в классе
 * */
export class RequestOrderDetails {
  public timeLines: OrderTimeline[] = [];
  public timelineErrorTitle: string = null;
  public timelineErrorMessage: string = null;
  public isCustomProductPage: boolean = false;
  public isAccepted = false;
  // ...
}

////////////////////////////////// 8 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 9 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 10 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 11 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 12 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 13 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 14 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 15 //////////////////////////////////
/**
 *
 * */


