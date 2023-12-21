////////////////////////////////// 1 //////////////////////////////////
/**
 * Информативный комментарий
 * */

// Паттерн валидации цвета в формате #XXXXXX
export const COLOR_PATTERN = '^#[0-9A-Fa-f]{6}$';

////////////////////////////////// 2 //////////////////////////////////
/**
 * Прояснение
 * */
function _transformProductType(productType: ProductTypeOverview) {
  // ...
  const { sizes, discount, parentType } = productType;

  sizes.forEach((size: ProductSize, index) => {
    /**
     * У продукта, со страндарным вариантом, отсутствуют поля price и productNumber,
     * поэтому эту значени необходимо взять из родительского size
     * */
    if (this._isStandardVariant(size.products)) {
      const { products, price, productNumber } = size;
      size = {
        ...size, products: products.map(p => ({ ...p, price, productNumber }))
      };
    }
  });
}

////////////////////////////////// 3 //////////////////////////////////
/**
 * Комментарии TODO
 * */
// TODO необходимо перенести в ui/utils-package
export const matchingToValidator = (key: string, equalToKey: string) => {
  // ...
};


////////////////////////////////// 4 //////////////////////////////////
/**
 * Комментарии TODO
 * */
// TODO: убрать условие, когда mailDelivery станет доступна
if (isMarketplaceProject) {
  deliveryInfo.mailDeliveryPointsExists = false;
}

////////////////////////////////// 5 //////////////////////////////////
/**
 * Комментарии TODO
 * */
// TODO: добавить интерфейсы к полям класса
export class ChoosePaymentType {
  @Input() public user: any;
  @Input() public paymentMethods: any[];
  @Input() public blockedOptions: any[];
  // ...
}

////////////////////////////////// 6 //////////////////////////////////
/**
 * Прояснение
 * */
export class UiMultiCheckboxComponent {
  // ...
// Колличество эллементов, отображаемых в свернутом состоянии блока
  @Input() public collapseItemsQuantity = 4;
// ...
}

////////////////////////////////// 7 //////////////////////////////////
/**
 * Предупреждение
 * */

// Устаревший метод, для обратной совместимости, в новых версиях испльзуйте getCategoriesForBakery
function getCategories(bakeryId: number, locale?: string): Promise<FilterCategory[]> {
  return this._productApi.getCategories(bakeryId, { locale })
    .then((categories: FilterCategory[]) => {
      categories = this._transformCategories(categories);
      this.categories = categories;
      return categories;
    });
}

////////////////////////////////// 8 //////////////////////////////////
/**
 * Предупреждение
 * */

export interface Bakery {
  // ...
  // Не использовать это поле, в новых версиях api его не будет
  invoiceWithoutApprove?: boolean;
  // ...
}

////////////////////////////////// 9 //////////////////////////////////
/**
 * Представление намерений
 * */
// Отправляем запрос до тех пор, пока вендорное api не подтвердит ордер
function _initOrderStatusFetcher() {
  let isStopped = false;
  const start = async (sid) => {
    const data = await this._ordersManager.fetchOrderStatus(sid);
    if (data && data.orderStatus === PAYMENT_STATUSES.CONFIRMED) {
      this.onPaid(sid);
    } else if (!isStopped) {
      setTimeout(() => start(sid), 1000);
    }
  };

  return { start, stop: () => isStopped = true };
}

////////////////////////////////// 10 //////////////////////////////////
/**
 * Прояснение
 * */
case APPLY_PROMO: {
  // ...
  // Если пользователь применил промокод, скидка от компании не применяется
  if (currentDiscount && currentDiscount.type === PROMO_CODE) {
    return { ...state };
  }

  if (currentDiscount && appliedDiscount.type === COMPANY_DISCOUNT) {
    // Если применяется дискаунт от компании, но персональный дискаунт больше
    // оставляем персональный дискаунт
    if (currentDiscount.type !== appliedDiscount.type && currentDiscount.value > appliedDiscount.value) {
      return { ...state };
    }
    // ...
  }
}
////////////////////////////////// 11 //////////////////////////////////
/**
 * Информативный комментарий
 * */
// Допустимые значения для цветовой палитры
export type ThemePalette = 'primary' | 'secondary' | 'outline-primary' | 'outline-secondary' | undefined;

////////////////////////////////// 12 //////////////////////////////////
/**
 * Представление намерений
 * */
// Вызываем события onDirty и onFormInvalid, потому что сторонний редактор ckeditor
// не генерирует эти события
this._valueChanges$ = this.themeForm.valueChanges.subscribe((theme) => {
  this.onChanged.emit({ theme });
  this.onDirty.emit(true);
  this.onFormInvalid.emit(this.themeForm.invalid);
});
