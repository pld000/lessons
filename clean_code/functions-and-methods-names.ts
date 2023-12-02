////////////////////////////////// 1 //////////////////////////////////
/**
 * Имя функции "анализироватьДату" не отражает, что она делает на самом деле,
 * по сути функция выполняет конвертацию/трансформацию входной строки в тип Date
 */
// Before
function parseDate(str: string, format = 'DD.MM.YYYY'): Date {
  return moment(str, format).toDate();
}

// After
function convertStringToDate(str: string, format = 'DD.MM.YYYY'): Date {
}

////////////////////////////////// 2 //////////////////////////////////
/**
 * Имя фунскии не является глаголным словосочетанием
 * */
// Before
function fromDateToStr(date: Date, format: string = 'DD.MM.YYYY'): string {
  return moment(date).format(format);
}

// After
function convertDateToString(date: Date, format: string = 'DD.MM.YYYY'): string {
}

////////////////////////////////// 3 //////////////////////////////////
/**
 * Слово get в имени функции является не точным, нужно именно не получить пароль,
 * а сгенерировать
 * */
// Before
function getPass(length: number): string {
  const charset = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
  let retVal = '';
  for (let i = 0, n = charset.length; i < length; ++i) {
    retVal += charset.charAt(Math.floor(Math.random() * n));
  }
  return retVal;
}

// After
function generatePassword(length: number): string {
}

////////////////////////////////// 4 //////////////////////////////////
/**
 * Некоректное имя для предиката
 * */
// Before
function checkIfFileHeicFormat(file: File): boolean {
  return (/\.(heic|heif)$/i).test(file.name.toLowerCase());
}

// After
function isHeicFile(file: File): boolean {
  return (/\.heic$/i).test(file.name.toLowerCase());
}

////////////////////////////////// 5 //////////////////////////////////
/**
 * Имя функции говорит, что функция делает query параметры из строки. На самом деле
 * функция на вход получает строку с query параметрами, парсит/разбирает строку и
 * возвращает словарь { имяПараметра: значениеПараметра }
 * */
// Before
function makeQueryParamsFromString(queryString: string): { [key: string]: any } {
  return queryString
    .split('&')
    .map(str => str.split('='))
    .reduce((acc, paramsAr) => ({ ...acc, [paramsAr[0]]: paramsAr[1] }), {});
}

// After
function parseQueryString(queryString: string): { [key: string]: any } {
}

////////////////////////////////// 6 //////////////////////////////////
/**
 * Некоректное имя предеката, непонятно что за проверка выполняется
 * */
// Before
function checkSize(size: string): boolean {
  if (!size) {
    return false;
  }

  return String(size).indexOf('-') === -1 || every(size.split('-'), v => Boolean(v));
}

// After
function isSizeValid(size: string): boolean {
}

////////////////////////////////// 7 //////////////////////////////////
/**
 * Для большей ясности, необходимо добавить в имя функции объект для котрого выполняется действие
 * */
// Before
function updateDeliveryPrice(zoneDeliveryPrice: IZoneDeliveryPrice,
                             deliveryZones: IDeliveryZone[]): IDeliveryZone[] {
  return deliveryZones
    .map(zone => zone.id === zoneDeliveryPrice.deliveryZone
      ? {
        ...zone,
        deliveryPrices: zone.deliveryPrices
          .map(d => d.type === zoneDeliveryPrice.type ? { ...d, ...zoneDeliveryPrice } : { ...d })
      }
      : { ...zone });
}

// After
function updateDeliveryPriceForDeliveryZone(zoneDeliveryPrice: IZoneDeliveryPrice,
                                            deliveryZones: IDeliveryZone[]): IDeliveryZone[] {
}

////////////////////////////////// 8 //////////////////////////////////
/**
 * Функция с побочными эффектами, слишком длинное название
 * */
// Before
function prepareDataFromDeliveryInfoUpdatePayload(payload: IFreeDeliveryInfo, cart: ICart) {
  let {
    freeDeliveryEnabled = null,
    freeDeliveryAllowsOnlyForCompanies = null,
    minCartPriceAllowsFreeDelivery = null,
    newDeliveryPrice = null,
  } = payload;

  freeDeliveryEnabled = freeDeliveryEnabled || cart.freeDeliveryEnabled;
  freeDeliveryAllowsOnlyForCompanies = freeDeliveryAllowsOnlyForCompanies || cart.freeDeliveryAllowsOnlyForCompanies;
  minCartPriceAllowsFreeDelivery = minCartPriceAllowsFreeDelivery || cart.minCartPriceAllowsFreeDelivery;

  const freeDeliveryCanBeApplied = freeDeliveryEnabled
    && (!freeDeliveryAllowsOnlyForCompanies || freeDeliveryAllowsOnlyForCompanies && cart.company);
  let totalPriceForChecking = freeDeliveryCanBeApplied ? getOrderLinesTotalPrice(cart.lines) : 0;

  const delivery = cart.lines.length > 0
    ? {
      ...cart.delivery,
      price: freeDeliveryCanBeApplied && totalPriceForChecking >= minCartPriceAllowsFreeDelivery
        ? 0
        : newDeliveryPrice || (cart.delivery && cart.delivery.price),
    }
    : null;

  return { delivery, freeDeliveryEnabled, freeDeliveryAllowsOnlyForCompanies, minCartPriceAllowsFreeDelivery };
}

// After
function mergeFreeDeliveryPayloadWithCartFreeDelivery(payload: IFreeDeliveryInfo, cart: ICart): IFreeDeliveryInfo {
  let {
    freeDeliveryEnabled = null,
    freeDeliveryAllowsOnlyForCompanies = null,
    minCartPriceAllowsFreeDelivery = null,
  } = payload;

  freeDeliveryEnabled = freeDeliveryEnabled || cart.freeDeliveryEnabled;
  freeDeliveryAllowsOnlyForCompanies = freeDeliveryAllowsOnlyForCompanies || cart.freeDeliveryAllowsOnlyForCompanies;
  minCartPriceAllowsFreeDelivery = minCartPriceAllowsFreeDelivery || cart.minCartPriceAllowsFreeDelivery;

  return { freeDeliveryEnabled, freeDeliveryAllowsOnlyForCompanies, minCartPriceAllowsFreeDelivery };
}

function getActualDeliveryInfo(payload: IFreeDeliveryInfo, cart: ICart): ICartDelivery {
  let {
    freeDeliveryEnabled, freeDeliveryAllowsOnlyForCompanies, minCartPriceAllowsFreeDelivery, newDeliveryPrice,
  } = payload;

  const freeDeliveryCanBeApplied = freeDeliveryEnabled
    && (!freeDeliveryAllowsOnlyForCompanies || freeDeliveryAllowsOnlyForCompanies && cart.company);
  let totalPriceForChecking = freeDeliveryCanBeApplied ? getOrderLinesTotalPrice(cart.lines) : 0;

  return cart.lines.length > 0
    ? {
      ...cart.delivery,
      price: freeDeliveryCanBeApplied && totalPriceForChecking >= minCartPriceAllowsFreeDelivery
        ? 0
        : newDeliveryPrice || (cart.delivery && cart.delivery.price),
    }
    : null;
}

////////////////////////////////// 9 //////////////////////////////////
/**
 * Некоректное имя метода. add - не выполняет сложения, более правильным будет имя append
 * */
// Before
class DeliveryZoneList {
  public add(zone: IDeliveryZone): void {
    if (this.isValid(zone)) {
      this._zones.push(zone);
    }
  }
}

// After
class DeliveryZoneList {
  public append(zone: IDeliveryZone): void {
    if (this.isValid(zone)) {
      this._zones.push(zone);
    }
  }
}

////////////////////////////////// 10 //////////////////////////////////
/**
 * Нет необходимости включать имя объекта в имя метода
 * */
// Before
class DeliveryZoneList {
  public removeDeliveryZone(zoneId: number): void {
    this._deliveryZonesApi.removeZone(zoneId);
  }
}

// After
class DeliveryZoneList {
  public remove(zoneId: number): void {
    this._deliveryZonesApi.removeZone(zoneId);
  }
}

////////////////////////////////// 11 //////////////////////////////////
/**
 * Имя полность отражает суть функции, но можно дать более выразительное имя
 * */
// Before
function updateOrCreateProductType(productType: IProductType): Promise<IProductType> {
  if (productType.id) {
    return this._productsApi.updateProductType(productType.id, productType)
      .toPromise();
  } else {
    return this._productsApi.createProductType(productType)
      .toPromise();
  }
}

// After
function saveProductType(productType: IProductType): Promise<IProductType> {
}

////////////////////////////////// 12 //////////////////////////////////
/**
 * Исходя из имени функции, функция должна вернуть список продуктов удовлетворяющих условию,
 * но на самом деле вернет строку
 * */
// Before
function getUnavailableProductsListFromOutlets(outlets: IOutlet[]): string {
  const products = outlets
    .map(({ unavailableProductTypes }) => unavailableProductTypes.length > 0)
    .reduce((result, value) => result.concat(value), []);

  return uniqBy(products, 'id')
    .map(p => p.name)
    .join(', ');
}

// After
function getOutletsUnavailableProductsString(outlets: IOutlet[]): string {
}
