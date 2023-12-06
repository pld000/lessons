////////////////////////////////// 1 //////////////////////////////////
/**
 *  Избавился от магических чисел
 */
// Before
async function _loadOrders() {
  const params = {
    limit: 100,
    offset: 100 * this.pageIndex
  };

  this.orders = await this._ordersApi.getOrders(this.bakery.id, params).toPromise();
}

// After
const DEFAULT_PAGE_SIZE = 100;

async function _loadOrders() {
  const params = {
    limit: DEFAULT_PAGE_SIZE,
    offset: DEFAULT_PAGE_SIZE * this.pageIndex
  };

  this.orders = await this._ordersApi.getOrders(this.bakery.id, params).toPromise();
}

////////////////////////////////// 2 //////////////////////////////////
/**
 * Улучшил использование URL адрессов, стало проще вносить изменения
 * */
// Before
const routes: Routes = [
  { path: 'information', component: CompanyInformationComponent },
  { path: 'web-shop-access', component: CompanyWebshopAccessComponent },
  { path: 'products', component: CompanyProductsComponent }
];

// After
const COMPANY_PAGES_ROUTES = {
  HOME: 'home',
  INFORMATION: 'information',
  WEB_SHOP_ACCESS: 'web-shop-access',
  PRODUCTS: 'products',
};

const routes: Routes = [
  { path: COMPANY_PAGES_ROUTES.INFORMATION, component: CompanyInformationComponent },
  { path: COMPANY_PAGES_ROUTES.WEB_SHOP_ACCESS, component: CompanyWebshopAccessComponent },
  { path: COMPANY_PAGES_ROUTES.PRODUCTS, component: CompanyProductsComponent }
];

////////////////////////////////// 3 //////////////////////////////////
/**
 * Улучшил использование навигации по страницам приложения в шаблоне компонента
 * */
// Before
const menu = `
<div class="top-menu">
  <a class="top-menu-item" [routerLink]="'information'">Information</a>
  <a class="top-menu-item" [routerLink]="'web-shop-access'">Information</a>
  <a class="top-menu-item" [routerLink]="'products'">Information</a>
</div>
`;

// After
const menu = `
<div class="top-menu">
  <a class="top-menu-item" [routerLink]="COMPANY_PAGES_ROUTES.INFORMATION">Information</a>
  <a class="top-menu-item" [routerLink]="COMPANY_PAGES_ROUTES.WEB_SHOP_ACCESS">Information</a>
  <a class="top-menu-item" [routerLink]="COMPANY_PAGES_ROUTES.PRODUCTS">Information</a>
</div>
`;

////////////////////////////////// 4 //////////////////////////////////
/**
 * Улучшил использование навигации по страницам приложения
 * */
// Before
function ngOnInit() {
  if (!this.company.privatePagesEnable) {
    this._router.navigate(['home']);
  }
}

// After
function ngOnInit() {
  if (!this.company.privatePagesEnable) {
    this._router.navigate([COMPANY_PAGES_ROUTES.HOME]);
  }
}


////////////////////////////////// 5 //////////////////////////////////
/**
 * Исправил наименование одинаковых констант, с разными смысловыми значениями
 * */
// Before
export const COUNTRY_CODE = {
  NORWAY: 'no',
  SWEDEN: 'se',
};

export const COUNTRY_CODE = {
  NO: 'no',
  SE: 'sv'
};

// After
export const COUNTRY_CODE = {
  NORWAY: 'no',
  SWEDEN: 'se',
};

export const COUNTRY_LOCALE = {
  NO: 'no',
  SE: 'sv'
};

////////////////////////////////// 6 //////////////////////////////////
/**
 * Вынес стандартные настройки приложения в константу, для более эффективного использования настроек
 * */
export const DEFAULT_CONFIG = {
  production: false,
  serviceWorker: false,
  countryDomainsMap: {
    'SE': 'se',
    'NO': 'no',
    'no': 'NO',
    'se': 'SE'
  },
  availableDomains: ['no', 'se'],
  defaultDomainName: 'no',
  // ...
};

////////////////////////////////// 7 //////////////////////////////////
/**
 * Избавился от магических чисел
 * */
// Before
export const emailMisspelled = (lengthDiffMax = 3, maxMisspelled = 2) => {
  // ...
};

// After
const DEFAULT_LENGTH = 3;
const MAX_MISSPELLED = 2;

export const emailMisspelled = (lengthDiffMax = DEFAULT_LENGTH, maxMisspelled = MAX_MISSPELLED) => {
  // ...
};

////////////////////////////////// 8 //////////////////////////////////
/**
 * Избавился от магических чисел
 * */
// Before
export const fetchOrderStatus = (orderId: number, tryCount = 100): ORDER_STATUSES => {
  // ...
  setTimeout(() => fetchOrderStatus(orderId, tryCount - 1));
  // ...
};

// After
const FETCH_ORDER_MAX_TRYING = 100;
export const fetchOrderStatus = (orderId: number, tryCount = FETCH_ORDER_MAX_TRYING): ORDER_STATUSES => {
  // ...
  setTimeout(() => fetchOrderStatus(orderId, tryCount - 1));
  // ...
};

////////////////////////////////// 9 //////////////////////////////////
/**
 * Улучшил использование типов событий обновления состояния
 * */
// Before
export function cartReducer(state = initialCartState, action: CartAction): CartState {
  switch (action.type) {
    // ...
    case 'Load Cart Success': {
      // ...
    }
    case 'Load Cart Fail': {
      // ...
    }
    // ...
  }
}

// After
export function cartReducer(state = initialCartState, action: CartAction): CartState {
  switch (action.type) {
    // ...
    case LOAD_CART_SUCCESS: {
      // ...
    }
    case LOAD_CART_FAIL: {
      // ...
    }
  }
}

////////////////////////////////// 10 //////////////////////////////////
/**
 * Улучшил использование типов событий для оповещения об изменении состояния
 * */
// Before
export class LoadCartSuccess implements Action {
  readonly type = 'Load Cart Success';

  constructor(public payload: ICart) {
  }
}

export class LoadCartFail implements Action {
  readonly type = 'Load Cart Fail';

  constructor(public payload: any) {
  }
}

// After
export class LoadCartSuccess implements Action {
  readonly type = LOAD_CART_SUCCESS;

  constructor(public payload: ICart) {
  }
}

export class LoadCartFail implements Action {
  readonly type = LOAD_CART_FAIL;

  constructor(public payload: any) {
  }
}

////////////////////////////////// 11 //////////////////////////////////
/**
 * Избавился от магических чисел
 * */
// Before
function initDateRange(): { begin: NgbDate, end: NgbDate } {
  return baDateHelper.initDateRange(-6, 0, -15, 0);
}

// After
const AMOUNT_MONTH_BEGIN = -6;
const AMOUNT_DAYS_BEGIN = -15;
const AMOUNT_MONTH_END = 0;
const AMOUNT_DAYS_END = 0;

function initDateRange(): { begin: NgbDate, end: NgbDate } {
  return baDateHelper.initDateRange(AMOUNT_MONTH_BEGIN, AMOUNT_MONTH_END, AMOUNT_DAYS_BEGIN, AMOUNT_DAYS_END);
}

////////////////////////////////// 12 //////////////////////////////////
/**
 * Улучшил обновление кода при изменении условий поиска со стороны бэкенда
 * */
// Before
function initCriteriaModel(): OrdersSearchCriteria {
  return  {
    deliveryMethod: 'deliveryToDoor',
    bakeryPaymentMethod: 'bakeryInvoice',
    salesChannel: 'WEB_SHOP',
    dateRange: initDateRange(),
    searchQuery: '',
    source: '',
    isInvoiceView: false,
  };
}

// After
function initCriteriaModel(): OrdersSearchCriteria {
  return  {
    deliveryMethod: ORDER_DELIVERY_METHODS.DELIVERY_TO_DOOR,
    bakeryPaymentMethod: BAKERY_PAYMENT_METHODS.BAKERY_INVOICE,
    salesChannel: SALES_CHANNELS.WEB_SHOP,
    dateRange: initDateRange(),
    searchQuery: '',
    source: '',
    isInvoiceView: false,
  };
}
