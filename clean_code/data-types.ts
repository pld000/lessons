////////////////////////////////// 1 //////////////////////////////////
/**
 * Улучшил проверку изменения цены, округлив цены до двух знаков
 * */
function isAddOnPriceChanged(beforeChanges: AddOn, afterChanges: AddOn): boolean {
  return Number(beforeChanges.price).toFixed(2) !== Number(afterChanges.price).toFixed(2);
}

////////////////////////////////// 2 //////////////////////////////////
/**
 * Добавил проверку для discountPercent, чтобы избежать делания на 0
 * Округлил результат до двух знаков, для удобства изспользования цены
 * */
function restoreCakePrice(price: number, discountPercent: number): number {
  return discountPercent > 0
    ? +Number(price * 100 / discountPercent).toFixed(2)
    : price;
}

////////////////////////////////// 3 //////////////////////////////////
/**
 * Заменил в условных конструкциях строковые значения на константы
 * */
function isDeliveryFinished(cart: Cart): boolean {
  const { delivery: { orderId, outletId, timeRange, date, type, postNumber, deliveryAgentId } } = cart;

  if (type === DELIVERY_OPTIONS.PICKUP) {
    return Boolean(orderId) && Boolean(timeRange) && Boolean(date) && Boolean(outletId);
  }

  if (type === DELIVERY_OPTIONS.DELIVERY) {
    return Boolean(orderId) && Boolean(timeRange) && Boolean(date) && Boolean(postNumber) && Boolean(deliveryAgentId);
  }

  return false;
}

////////////////////////////////// 4 //////////////////////////////////
/**
 * Заменил повторяющиеся условия логической переменой, для повешения читабельности программы
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

  return false;
}

////////////////////////////////// 5 //////////////////////////////////
/**
 * Избавился от произвольного типа Any, вместо него добавил интерфейс ProductsRequestParams,
 * улучшил типизацию внутри функции
 * */
export interface ProductsRequestParams {
  bakeryId: number,
  categoryId?: number,
  upsellOnly?: any,
  startDate: any,
  endDate: any,
  offset: number,
  limit: number,
}

async function _loadProductsPerformance() {
  const params = {
    ...this._getQueryParams(),
    bakeryId: await this._getBakeryId(),
    limit: this.pageSize,
    offset: this.pageIndex * this.pageSize
  } as ProductsRequestParams;

  this.products = await this._productsApi.getProducts(params).toPromise();
}

////////////////////////////////// 6 //////////////////////////////////
/**
 * Добавил базовый интерфейс, для более ясного типизирования функции
 * */
// Before
const _mapDeliveryOrder = (delivery: PickupDelivery | DeliveryToDoor): PickupDelivery | DeliveryToDoor => {
  const { outlet, deliveryAgent, availableDisabledDays } = delivery;

  return {
    ...delivery,
    outlet: outlet && outlet.id ? outlet.id : outlet,
    deliveryAgent: availableDisabledDays ? null : deliveryAgent
  } as any;
};

// After
interface Delivery {
  id?: number;
  type: string;
  number: string;
  price: number;
  priceWithoutVat: number;
  outlet: IOutlet;
  deliveryAgent?: any;
  deliveryAddress: string;
  availableDisabledDays: boolean;
  // ...
}

interface PickupDelivery extends Delivery {
  // ...
}

interface DeliveryToDoor extends Delivery {
  // ...
}

const _mapDeliveryOrder = (delivery: Delivery): Delivery => {
  const { outlet, deliveryAgent, availableDisabledDays } = delivery;

  return {
    ...delivery,
    outlet: outlet && outlet.id ? outlet.id : outlet,
    deliveryAgent: availableDisabledDays ? null : deliveryAgent
  } as Delivery;
};

////////////////////////////////// 7 //////////////////////////////////
/**
 * Добавил проверку для completedOrdersCount, чтобы избежать делания на 0
 * Округлил результат до двух знаков, большее количество знаков не имеет смысла
 * */
function getBreakage(pendingOrdersCount, completedOrdersCount): string {
  return completedOrdersCount > 0
    ? ((pendingOrdersCount / completedOrdersCount) * 100).toFixed(2) + ' %'
    : 0 + '%';
}

////////////////////////////////// 8 //////////////////////////////////
/**
 * Добавил логические переменные (companyPayCommission, bakeryPayCommission)
 * улучшил читаемость кода
 * */
function getInvoiceOption(): INVOICE_SETTINGS_OPTIONS {
  const { DEACTIVATED, BAKERY_HANDLE, COMPANY_HANDLE } = INVOICE_SETTINGS_OPTIONS;
  const { invoiceEnabledOnWebShop, companyInvoiceEnabledOnWebShop } = this.bakery;
  const companyPayCommission = invoiceEnabledOnWebShop && companyInvoiceEnabledOnWebShop;
  const bakeryPayCommission = invoiceEnabledOnWebShop && !companyInvoiceEnabledOnWebShop;

  if (companyPayCommission) {
    return COMPANY_HANDLE;
  } else if (bakeryPayCommission) {
    return BAKERY_HANDLE;
  }

  return DEACTIVATED;
}

////////////////////////////////// 9 //////////////////////////////////
/**
 * Избавился от произвольного типа Any, вместо него добавил интерфейс UserCredentials
 * для правилього приведения типов
 * */
interface UserCredentials {
  email: string;
  password: string;
  bakery?: number;
}

function register(user: IUser): Promise<IUser> {
  let credentials = { email: user.email, password: user.password } as UserCredentials;

  if (user.bakery) {
    credentials = { ...credentials, bakery: user.bakery };
  }
  return this._users
    .register(user)
    .then(() => this.login(credentials));
}

////////////////////////////////// 10 //////////////////////////////////
/**
 * Улучшил читаемость кода, добавив логичекую переменную isFreeDeliveryUseful
 * */
function getDeliveryPriceAfterLinesUpdate(cart: Cart): number {
  const {
    freeDeliveryInfo: { freeDeliveryEnabled, minCartPriceAllowsFreeDelivery, freeDeliveryAllowsOnlyForCompanies },
    delivery: { price: deliveryPrice }, company, lines
  } = cart;

  const orderLinesPriceTotal = getOrderLinesTotalPrice(lines);
  const isFreeDeliveryUseful = freeDeliveryEnabled && orderLinesPriceTotal >= minCartPriceAllowsFreeDelivery
    && (!freeDeliveryAllowsOnlyForCompanies || freeDeliveryAllowsOnlyForCompanies && company);

  return isFreeDeliveryUseful ? 0 : deliveryPrice;
}

////////////////////////////////// 11 //////////////////////////////////
/**
 * Заменил строковые значения в ngSwitchCase на строковые константы,
 * улучшил скорость внесения изменений в приложение
 * */
const template = `
<ng-container [ngSwitch]="channel">
  <span class="nowrap badge badge-short badge-default"
        *ngSwitchCase="CHANNEL_TYPES.EXPRESS">
    Take away
  </span>
  <span class="nowrap badge badge-short badge-default"
        *ngSwitchCase="CHANNEL_TYPES.STANDARD">
    Pre ordered
  </span>
</ng-container>
`;

////////////////////////////////// 12 //////////////////////////////////
/**
 * Добавил базовый тип для всех дополнительных товорав у продукта, это улучшило
 * читаемость кода для методов и функций, которые выполняют действия над доп. товарами продукта
 * (в частности это улучшение позволило избавиться от перечесления всех типов доп. товаров для
 * входных параметров функции)
 * */
export interface AddOn {
  // ...
}

export interface ExtraProduct extends AddOn {
  // ...
}

export interface PrintingPicture extends AddOn {
  // ...
}

export interface PrintingText extends AddOn {
  // ...
}

export interface Deposit extends AddOn {
  // ...
}

function mapAddOnsToDefaultValues (addOn: IAddOn) {
  // ...
}