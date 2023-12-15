////////////////////////////////// 1 //////////////////////////////////
/**
 * Изменил глобальную область видимости для переменных
 * categoryId, bakeryId, unpublishedParams на локальную
 * */
function getUnpublishedProductTypes(categoryId: number, bakeryId: number, params) {
  const unpublishedParams = { ...params, published: 'false' };
  return this._productsApi
    .getProductTypesForBakeryAdmin(bakeryId, categoryId, unpublishedParams)
    .then((productsTypes) => this.unpublishedQuantity = productsTypes);
}

////////////////////////////////// 2 //////////////////////////////////
/**
 * Объявление переменной categoryId, находилось за пределами if, а использовалась
 * внутри if, поэтому перенес ее объявление внутрь if блока, чтобы минимизировать область видимости
 * */
const categorySubscription = combineLatest([this._route.queryParams, this._categoriesManager.bakeryCategories$])
  .subscribe(([queryParams, categories]) => {
    this.categories = categories && categories.filter((c) => c.productTypesCount > 0);
    // ...
    if (this.categories && this.categories.length) {
      const { categoryId } = queryParams; //
      const currentCategory = [...this.categories, ...this.newCategories].find(({ id }) => id === categoryId);
      this.currentCategoryChanged$.next(currentCategory);
      // ...
    }
  });

////////////////////////////////// 3 //////////////////////////////////
/**
 * Перенес объявление переменной redirectToUrl из внешней функции
 * во внутреннюю ближе к месту ее использования, тем сам минимизировав
 * область видимости
 * */
function _subscribe() {
  // ...
  this._currentUserService.getDefaultRoute()
    .subscribe((route) => {
      const redirectToUrl = this._route.snapshot.queryParams.redirectTo;
      if (redirectToUrl) {
        return this._router.navigateByUrl(redirectToUrl);
      }
      // ...
      return this._router.navigateByUrl(route);
    });
}

////////////////////////////////// 4 //////////////////////////////////
/**
 * Изменил модификоторы доступа для _payload и _token с публичных на приватные,
 * ограничив область использования только внутри класса,
 * а для доступа к свойствам добавил публичные методы.
 * */
class CieAuthToken<T = any> {
  // ...
  private _payload: T = null;

  constructor(private readonly _token: string) {
    // ...
  }

  public getValue(): string {
    return this._token;
  }

  public getPayload(): T {
    return this._payload;
  }

  // ...
  public isValid(): boolean {
    return Boolean(this.getValue()) && Boolean(this.getPayload())
      && (!this.getTokenExpDate() || new Date() < this.getTokenExpDate());
  }

  // ...
}

////////////////////////////////// 5 //////////////////////////////////
/**
 * Избавился от промежуточной переменной url, в которую присваивался результат
 * метода getBakeryWebShopLoginUrl, затем url больше нигде не использовалась
 * */
async function login() {
  try {
    this._document.location.href = await this._bakeryApi
      .getBakeryWebShopLoginUrl(this._route.snapshot.queryParams.bakeryId);
  } catch (err) {
    this.error = err.message;
  }
}

////////////////////////////////// 6 //////////////////////////////////
/**
 * Заменил переменную this.bakeryId из глобального контекста, на локальную
 * переменную bakeryId
 * */
async function exportVisaOrders() {
  const bakeryId = this._bakeryService.selectedBakery$.value.id;
  try {
    const { dateBegin, dateEnd, includeExported } = await this._ordersModalManager
      .showVisaExportOrdersModal(bakeryId);

    return this._exportOrdersUrlGenerator
      .exportVisaOrders(bakeryId, dateBegin, dateEnd, includeExported);
  } catch (e) {
    throw e;
  }
}

////////////////////////////////// 7 //////////////////////////////////
/**
 * Вынес логику инициализации переменных из функции getInitialDateRangeValues
 * в отдельные функции, ограничив область видимости переменных внутри этих функций
 * */
function getInitialDateRangeValues(beginDate = null, endDate = null): { beginDate: Date, endDate: Date } {
  return { beginDate: getInitialBeginDate(beginDate), endDate: getInitialEndDate(endDate) };
}

function getInitialBeginDate(beginDate) {
  beginDate = beginDate
    ? moment(beginDate, 'YYYY-MM-DD').startOf('day').toDate()
    : moment().subtract(29, 'days').startOf('day').toDate();

  // ...
  return beginDate;
}

function getInitialEndDate(endDate) {
  endDate = endDate
    ? moment(endDate, 'YYYY-MM-DD').endOf('day').toDate()
    : moment().endOf('day').toDate();

  // ...
  return endDate;
}

////////////////////////////////// 8 //////////////////////////////////
/**
 * Перенес инициализацию переменых generalInfo$, persons$, timelines$
 * ближе к месту их использования
 * */
async function initBakeryInformationPage() {
  // ...
  const generalInfo$ = this._bakeryGeneralInfoApi.getInfo(this.bakery.id).toPromise();
  const persons$ = this._contactPersonsApi.getPersons(this.bakery.id).toPromise();
  const timelines$ = this._getTimelines();
  const [generalInfo, persons, timelines] = await (this.busy = Promise
    .all([generalInfo$, persons$, timelines$]));

  this.generalInfo = generalInfo;
  this.persons = persons;
  this.timelines = timelines;

  // ...
}

////////////////////////////////// 9 //////////////////////////////////
/**
 * Разбил функции changeDayOff на три функции, локализовав в них переменные
 * из функции changeDayOff
 * */
function changeDayOff() {
  updateDeadlineSchema();
  calculateDeadlineDay();
  updateSchedule();
}

function updateDeadlineSchema() {
  // ...
  const parsedSchedule = this.getSchedule();
  // ...
  return parsedSchedule
    .map(day => {
      const deadlineSchema = this._getOrderOnOptions(!day.dayOff);
      const deadline = deadlineSchema.find(s => s.value === day.deadline.value) || deadlineSchema[0];

      return { ...day, deadlineSchema, deadline };
    });
}

function calculateDeadlineDay() {
  const workingDaysCount = this.parsedSchedule.filter(day => !day.dayOff).length;
  this.parsedSchedule = this.parsedSchedule
    .map(day => ({ ...day, deadlineDay: this.getOrderOnDay(day, workingDaysCount) }));
}

function updateSchedule() {
  const schedule = this._buildSchedule(this.parsedSchedule);
  this.onScheduleUpdated.emit(schedule);
}

////////////////////////////////// 10 //////////////////////////////////
/**
 * Перенес инициализацию переменных isStandardCommissions, data из начала
 * функции к месту их использования
 * */
function prepareDataForRequest(formData: CommissionsFormData): BakeryCommission {
  // ...
  const mapCommission = () => COMMISSIONS_PAYMENTS_KEYS
    .filter(key => formData.commissions[key] || formData.commissions[key] == 0)
    .map(key => ({ type: key, value: formData.commissions[key] }));
  // ...
  const { isStandardCommissions } = formData;
  const data = { type: this.bakeryCommission.type, isStandardCommissions };
  return isStandardCommissions ? data : { ...data, commissions: mapCommission() };
}

////////////////////////////////// 11 //////////////////////////////////
/**
 * Изменил глобальную область видимости переменной config, на локальную
 * */
function getFormControlsConfigs(): { [key: string]: any } {
  const config = [
    { value: null, disabled: this.bakeryCommission.isStandardCommissions },
    [Validators.required, Validators.min(0), Validators.max(100)]
  ];

  return this.availablePayments
    .reduce((acc, paymentName) => (acc[paymentName] = config, acc), {});
}

////////////////////////////////// 12 //////////////////////////////////
/**
 * Изменил глобальную область видимости переменной paymentOrder, на локальную
 * */
function sortPaymentMethods() {
  const paymentOrder = {
    [COMMISSIONS_PAYMENTS_TYPES.BAMBORA]: 0,
    [COMMISSIONS_PAYMENTS_TYPES.KLARNA]: 1,
    [COMMISSIONS_PAYMENTS_TYPES.INVOICE]: 2,
    [COMMISSIONS_PAYMENTS_TYPES.PAY_IN_STORE]: 3,
  };

  this.availablePayments = this.availablePayments
    .sort((a, b) => paymentOrder[a] - paymentOrder[b]);
}

////////////////////////////////// 13 //////////////////////////////////
/**
 * Перенес инициализацию commissions в блок if, в котором commissions
 * непосредственно и используется
 * */
function changeChargeCommission() {
  // ...
  if (!this.charge) {
    // ...
    const commissions = Object.entries(this.commissionsForm.get('commissions').value)
      .reduce((acc, [key, val]) => (acc[key] = 0, acc), {});

    this.commissionsForm.patchValue({ isStandardCommissions: false, commissions });
    this.changeCommissionFormValue();
  }
}


////////////////////////////////// 14 //////////////////////////////////
/**
 * Перенес инициализацию переменных isFirstTimeActivate, isRefresh, isNewZone, zoneId, isDelete
 * к месту их использования
 * */
async function showDeliveryZoneDefinition(deliveryZoneId?: number) {
  try {
    // ...
    const isFirstTimeActivate = !Boolean(this.deliveryZones.length);
    const event = await this._deliveryModalManger
      .showDeliveryZoneDefine(this.legalCompanyId, this.countryCode, deliveryZoneId, isFirstTimeActivate);
    // ...
    const { isRefresh } = event;
    if (isRefresh) {
      await this._initDeliveryZones();
    }

    const isNewZone = !Boolean(deliveryZoneId);
    const { zoneId, isDelete } = event;
    if (isNewZone) {
      this.changeZone(this.deliveryZones.find(d => d.id === zoneId) || this.deliveryZones[0]);
    } else if (isDelete && this.deliveryZones.length) {
      this.changeZone(this.deliveryZones[0]);
    } else {
      this.changeZone(this.deliveryZones.find(d => d.id === this.selectedZone.id));
    }

  } catch (e) {
  }
}

////////////////////////////////// 15 //////////////////////////////////
/**
 * Перенес инициализацию переменных order, isBackDirection
 * к месту их использования
 * */
async function navigateTo(event: { item: any, queryParamsStr: string, isBackDirection: boolean }) {
  const { queryParamsStr } = event;
  const criteria = queryParamsStr
    ? makeQueryParamsFromString(queryParamsStr)
    : this.route.snapshot.queryParams;
  // ...
  const { item: order } = event;
  if (order) {
    return this.navigateToOrder(order.id, criteria);
  }
  // ...
  const response = await (this.busy = this._giftOrdersApi.getOrders(criteria).toPromise());
  const { isBackDirection } = event;
  const navigateToOrder = isBackDirection ? response.results[0] : last(response.results);
  await this.navigateToOrder(navigateToOrder.id, criteria);
  // ...
}

