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
 * Сделал явным объявление переменных (user, company, invoiceInfo, patch),
 * перенес объявление переменной patch, к месту ее использования
 * */
interface UserFormPatch {
  user: User,
  invoiceInfo: UserCompany,
  company: UserInvoiceInfo
}

async function initUserPage() {
  const { companyId, userId } = this._route.snapshot.params;
  const user: User = await this._usersCustomersApi.getUserById(userId);
  const company: UserCompany = await this._companyApi.getCompany(companyId);
  const invoiceInfo: UserInvoiceInfo = await this._usersCustomersApi.getUserInvoiceInfo(userId);

  this.userForm = this._getUserForm(this.bakeryId);
  const patch: UserFormPatch = this._getUserFormPatch({ user, invoiceInfo, company });
  this.userForm.patch(patch);
  // ...
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
 * Добавил затирание неиспользуемого значения
 * */
function ngOnInit() {
  const { bakeryId } = this._route.snapshot.params;
  this.bakery = this._bakeryApi.getBakeryById(bakeryId);
  // ...
}

// ...
function ngOnDestroy() {
  // ...
  this.bakery = null;
  // ...
}

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
 * Сделал объявления переменных явным, сделаш инициализацию счетчика цикла while,
 * непосредственно перед циклом, переименовал переменную i в цикле while на daysBeforeOrder
 * */
function getOrderOnDay(day: ScheduleDay, workingDaysCount: number): string {
  let dayIndex: number = this.parsedSchedule.findIndex(d => d.key === day.key);
  const deadlineDaysCount: number = day.deadline.value % workingDaysCount;
  const steps: number = deadlineDaysCount === 0 ? workingDaysCount : deadlineDaysCount;

  let daysBeforeOrder: number = 0;
  while (daysBeforeOrder !== steps) {
    const prevIndex = dayIndex - 1;
    dayIndex = prevIndex >= 0 ? prevIndex : prevIndex + 7;
    daysBeforeOrder = this.parsedSchedule[dayIndex].dayOff ? daysBeforeOrder : daysBeforeOrder + 1;
  }

  return this.parsedSchedule[dayIndex].key;
}

////////////////////////////////// 9 //////////////////////////////////
/**
 * Сделал инициализацию полей класса, улучшил типизацию заменив тип string
 * для все полей на типы соответствующего enum
 * */
export enum PAYMENT_STATUSES {
}

export enum PAYMENT_TYPES {
}

export enum PAYMENT_METHODS {
}

export class Order {
  public paymentStatus: PAYMENT_STATUSES = null;
  public paymentType: PAYMENT_TYPES = null;
  public paymentMethod: PAYMENT_METHODS = null;
  // ...
}

////////////////////////////////// 10 //////////////////////////////////
/**
 * Инициализировал аккомулирующую переменную price начальным значением
 * */
const getCustomOrderLineTotal = (orderLine: ICustomOrderLine): number => {
  const { customAddOns } = orderLine;
  let price: number = 0;

  customAddOns.forEach((addOn) => {
    price += addOn.price ? addOn.price : 0;
  });
  price += orderLine.price ? orderLine.price * 1 : 0;

  return price * orderLine.quantity;
};

////////////////////////////////// 11 //////////////////////////////////
/**
 * Заменил имя paramsAr на более наглядное имя queryParamsAr, добавил начальную инициализацию
 * queryParamsAr
 * */
const generateUrl = (snakedParams: { [key: string]: string }, apiUrl: string): string => {
  const queryParamsAr: string[] = [];

  Object.entries(snakedParams).forEach(([key, value]) => {
    if (value && Array.isArray(value)) {
      value.forEach(v => queryParamsAr.push(`${key}=${v}`));
    } else if (value) {
      queryParamsAr.push(`${key}=${value}`);
    }
  });

  return apiUrl + '?' + queryParamsAr.join('&');
};

////////////////////////////////// 12 //////////////////////////////////
/**
 * Добавил сообщение о недопустимом значении переменной, учлучших консткукцию
 * switch заменив строковые значения на значения из enum, улучших типизацию
 * входного параметра type
 * */
enum SANITIZER_TYPES {
  HTML = 'html',
  STYLE = 'style',
  SCRIPT = 'script',
  URL = 'url',
  RESOURCE_URL = 'resourceUrl',
}

function transform(value: any, type: SANITIZER_TYPES): SafeHtml | SafeStyle | SafeScript | SafeUrl | SafeResourceUrl {
  switch (type) {
    case SANITIZER_TYPES.HTML:
      return this.sanitizer.bypassSecurityTrustHtml(value);
    case SANITIZER_TYPES.STYLE:
      return this.sanitizer.bypassSecurityTrustStyle(value);
    case SANITIZER_TYPES.SCRIPT:
      return this.sanitizer.bypassSecurityTrustScript(value);
    case SANITIZER_TYPES.URL:
      return this.sanitizer.bypassSecurityTrustUrl(value);
    case SANITIZER_TYPES.RESOURCE_URL:
      return this.sanitizer.bypassSecurityTrustResourceUrl(value);
    default:
      throw new Error(`Invalid safe type specified: ${type}`);
  }
}

////////////////////////////////// 13 //////////////////////////////////
/**
 * Сделал объявление переменных originExtraProductsMap, backUpExtraProductsMap явным,
 * значения этих переменых не перезаписываются, поэтому заменил модификатор let на
 * модификатор const, для лучшего понимания кода
 * */
const mergeMappedLines = (mappedLine: CakeBuilderOrderLine, backupMappedLine: CakeBuilderOrderLine) => {
  // ...
  const originExtraProductsMap: { [key: number]: ExtraProduct } = mappedLine.extraProductsMap;
  const backUpExtraProductsMap: { [key: number]: ExtraProduct } = backupMappedLine.extraProductsMap;
  // ...
};

////////////////////////////////// 14 //////////////////////////////////
/**
 * Добавил начальную инициализацию полей в классе
 * */
export class InnerOffer {
  public isEdit: boolean = false;
  public scrollTarget: { type: string, id: number } = null;
  public isNew: boolean = false;
  public orderOffer: OfferOrderModel = null;
  public customOrderLine: CustomOrderLine = null;
  public orderLine: OrderLine = null;
  // ...
}

////////////////////////////////// 15 //////////////////////////////////
/**
 * Добавил корректное завершение работы с переменной textArea
 * */
export function copyToClipboard(textToCopy: string): Promise<boolean> {
  try {
    let textArea = document.createElement('textarea');

    // ...

    textArea = null;
    return Promise.resolve(true);
  } catch (err) {
    return Promise.resolve(false);
  }
}

