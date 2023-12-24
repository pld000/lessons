////////////////////////////////// 1 //////////////////////////////////
/**
 * Закомментированный код. Его нужно было раскомментировать, во время выполнения
 * задачи NS3-6280. Но потом концепция поменялась, задача отправилась в архив,
 * а код остался. Просто удалил комментарий.
 * */
function updateUpsellProduct(event: { upsellCartLineInfo: UpsellCartLineInfo; count: number }): void {
  // ...

  // TODO: расскоментировать, когда задача NS3-6280 будет в разработке
  // if (this.isCateringMenu) {
  //   this.onSaveCartLine.emit(this.upsellCartLinesByProductId[productId]);
  // }

  this._updateTotalPriceAndQuantity();
}

////////////////////////////////// 2 //////////////////////////////////
/**
 * Обязательные комментарии. В целях улучшения читаемости кода, удалил эти комментарии
 * */

export interface TabChangeEvent {
  /**
   * Id текущей астивной табы
   */
  activeId: string;

  /**
   * Id следующей табы
   */
  nextId: string;
  // ...
}


////////////////////////////////// 3 //////////////////////////////////
/**
 * Бормотание. Этот комментарий может понять, только разработчик, которые его написал.
 * Максимум, через две недели его уже ни кто не сможет понять. Для улучшения, сделал
 * более подробное описание.
 * */

// Улучшение
/**
 * Так как комиссии для типов PROCUREMENT и ADMIN должны иметь одни и те же значения, то исключим
 * из общего списка комиссий один из типов (пусть будет ADMIN).
 * */

async function _initCommissions() {
  // ...
  const bakeryCommissions = await (this.busy = this._commissionsApi.getCommissions(bakery.id).toPromise());

  this.bakeryCommissions = bakeryCommissions
    /**Потому что комиссии для PROCUREMENT и ADMIN одинаковы*/
    .filter(c => c.type !== COMMISSIONS_TYPES.ADMIN)
    .sort((a, b) => this._commissionsOrder[a.type] - this._commissionsOrder[b.type]);
}

////////////////////////////////// 4 //////////////////////////////////
/**
 * Комментарии там, где можно использовать функцию или переменную. Для улучшения,
 * можно удалить комментарий и переименовать метод в более информативный.
 * Например isBiggerThan(date)
 * */

class DeliveryDate {
  private _deliveryDate: Date;

  // ...
  /**
   * Возвращает true если дата доставки больше входного парамера
   * */
  public compareWith(date: Date) {
    return convertToMinutes(this._deliveryDate) > convertToMinutes(date);
  }
}


////////////////////////////////// 5 //////////////////////////////////
/**
 * Бормотание + Недостоверные комментарии. Для улучшения исправил комментарий
 * */

// Улучшение
/**
 * Если textQuery не пустой, то поиск элементов должен быть только с параметром textQuery,
 * остальные параметры сбрасываются.
 * */

function _prepareSearchCriteria(search: Partial<RequestsSearchRequestsContext>): SearchCriteria {
  // ...
  /**Не делать поиск если textQuery не пустой*/
  if (search && search.hasOwnProperty('textQuery')) {
    return criteria;
  }
  // ...
}

////////////////////////////////// 6 //////////////////////////////////
/**
 * Слишком много информации. Для улучшени необходимо убарать лишнюю информацию
 * */

// Улучшение
/**
 * Если выбранный size имеет deposit, то все deposit этого size по умолчанию должны
 * попасть в orderLine
 * */

function _reviseDeposits(size: Size) {
  const { deposits } = size;
  /**
   * Новый тип аддона - deposit. К size может быть добавлено несколько deposit. И если size имеет deposit,
   * то deposit должен по умолчанию попадать в orderLine, тогда как все другие аддоны, чтобы попасть
   * в orderLine должны быть выбранны пользователем. Необходима выбрать нужные deposit и положить
   * их в orderLine
   * */
  const sizeDepositsIds = deposits.map(d => d.id);
  const orderLineDepositsIds = this.mappedLine.orderLines
    .filter(l => l.type === ADDONS_TYPES.DEPOSIT)
    .map(l => l.deposit.id);
  // ...
  const orderLines = this.mappedLine.orderLines
    .filter(l => l.type !== ADDONS_TYPES.DEPOSIT
      || l.type === ADDONS_TYPES.DEPOSIT && sizeDepositsIds.includes(l.deposit.id));
}

////////////////////////////////// 7 //////////////////////////////////
/**
 * Шум. Абсолютно не нужный комментарий. Удалил его, для улучшения кода
 * */
function _subscribe() {
  this._subjectsSet['cake']
    .subscribe((cake) => {
      // ...

      /**Для того чтобы рассширить свойствами из size.*/
      const { sizes: [size] } = cake;
      this.mappedLine = { ...this.mappedLine, ...size };
      // ...
    });
}

////////////////////////////////// 8 //////////////////////////////////
/**
 * Обязательные комментарии. Для улучшения читаемости кода, удалил комментарии
 * */
export class UiPaginatorIntl {
  /** A label for the page size selector. */
  itemsPerPageLabel: string = 'Items per page:';

  /** A label for the button that increments the current page. */
  nextPageLabel: string = 'Next page';

  /** A label for the button that decrements the current page. */
  previousPageLabel: string = 'Previous page';

  /** A label for the button that moves to the first page. */
  firstPageLabel: string = 'First page';

  /** A label for the button that moves to the last page. */
  lastPageLabel: string = 'Last page';
}

////////////////////////////////// 9 //////////////////////////////////
/**
 * Шум. Комментарий утверждает очевидное, для улучшения кода, удалил его.
 * */

const recoverPricesFromOrderLine = (cake: Cake, orderLine: OrderLine): Cake => {
  // ...
  const { discount, price } = orderLine;
  /** Если цена ордерЛайна изменилась, сумма скидки должна быть пересчитана*/
  orderLine.discountAmount = calcDiscount(price, discount);
  // ...
};

////////////////////////////////// 10 //////////////////////////////////
/**
 * Обязательные комментарии. Удалил для улучшения кода.
 * */

/**
 * Returns found location
 * @param countryCode
 * @param postNumber
 */
function searchLocationByPostNumber(countryCode: string, postNumber: string): Observable<City | null> {
  return this.api
    .get('location', 'post_numbers_one_city', { countryCode, postNumber });
}

////////////////////////////////// 11 //////////////////////////////////
/**
 * Недостоверные комментарии. Изменил информацию на более достоверную
 * */
// Улучшение
/**Поля comment и name являются обязательными, и если пользователь их
 * не заполнил, в пэйлоад апи запроса, это поля будут с пустыми значениями */

// ...
/**Так как поля comment и name являются обязательными, и если пользователь заполнил
 * только поле image, то поля comment и name не попадут в пэйлоад апи запроса*/
const getRequiredField = (addOn) => {
  switch (addOn.type) {
    case COMMENT:
      const { comment = '' } = addOn.details;
      return { comment };
    case  EXTRA_PRODUCT:
      const { name = '' } = addOn.details;
      return { name };
  }
};

////////////////////////////////// 12 //////////////////////////////////
/**
 * Закомментированный код. Метод когда то использовался, и комментарий к нему был актуальным.
 * Но код изменился, а метод, возможно, времено закоментировали. Удалил закомментированный код и комментарий к нему.
 * */

export class ComplexManager extends BuilderManagerBase {
  // ...

  /**Для совместимости с PiecesManager, PiecesGroupManager*/
  // public upsertPiecesGroupProduct(...args) {
  //   return Promise.resolve(null);
  // }

  // ...
}

////////////////////////////////// 13 //////////////////////////////////
/**
 * Обязательные комментарии. Для улучшения читаемости кода, удалил комментарии
 * */
export class Tabset implements AfterContentChecked {
  /**
   * Whether the closed tabs should be hidden without destroying them
   */
  @Input() destroyOnHide = true;

  /**
   * The orientation of the nav (horizontal or vertical).
   */
  @Input() orientation: 'horizontal' | 'vertical';

  /**
   * Type of navigation to be used for tabs. Can be one of 'tabs' or 'pills'.
   */
  @Input() type: 'tabs' | 'pills';

  /**
   * A tab change event fired when the tab selection happens.
   */
  @Output() tabChange = new EventEmitter<TabChangeEvent>();
}

////////////////////////////////// 14 //////////////////////////////////
/**
 * Нелокальная информация. Изменил комментарий
 * */

// Улучшение
/**В деталях ордера необходимо показывать оригинальную цену продукта, до
 * применения скидки*/

function _restorePrice() {
  /** Из за того что в базе данных не храниться изначальная цена продукта без скидки,
   * а ответе апи в ордере находится цена продукта с примененной скидкой, то при показе
   * ордера необходимо паказывать восстановленную цену продукта*/
  const percent = 100 - this.discountOrigin;
  this.price = this.order.multiOrder.price * 100 / percent;
  this.priceWithoutVat = this.order.multiOrder.priceWithoutVat * 100 / percent;
}

////////////////////////////////// 15 //////////////////////////////////
/**
 * Шум. Для улучшения удалил комментарии.
 * */

// EXTRA_PRODUCT prices
if (line.extraProducts.length) {
  this.price += count * (line.extraProducts
    .map((ep) => ep.product.price)
    .reduce((pr, cu) => pr + cu, 0));

  this.priceWithoutVat += count * (line.extraProducts
    .map((ep) => ep.product.priceWithoutVat || 0)
    .reduce((pr, cu) => pr + cu, 0));
}

// PRINTING_TEXT price
if (line.printingTextValue) {
  this.price += count * line.model.printingText.price;
  this.priceWithoutVat += count * line.model.printingText.priceWithoutVat || 0;
}

// PRINTING_PICTURE price
if (line.model.printingPicture && line.model.printingPictureUrl) {
  this.price += count * line.model.printingPicture.price;
  this.priceWithoutVat += count * line.model.printingPicture.priceWithoutVat || 0;
}
