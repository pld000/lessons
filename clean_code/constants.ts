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
}

export const COUNTRY_CODE = {
  NO: 'no',
  SE: 'sv'
}

// After
export const COUNTRY_DOMAIN = {
  NORWAY: 'no',
  SWEDEN: 'se',
}

export const COUNTRY_LOCALE = {
  NO: 'no',
  SE: 'sv'
}

////////////////////////////////// 6 //////////////////////////////////
/**
 *
 * */
// Before

// After

////////////////////////////////// 7 //////////////////////////////////
/**
 *
 * */
// Before

// After

////////////////////////////////// 8 //////////////////////////////////
/**
 *
 * */
// Before

// After

////////////////////////////////// 9 //////////////////////////////////
/**
 *
 * */
// Before

// After

////////////////////////////////// 10 //////////////////////////////////
/**
 *
 * */
// Before

// After

////////////////////////////////// 11 //////////////////////////////////
/**
 *
 * */
// Before

// After

////////////////////////////////// 12 //////////////////////////////////
/**
 *
 * */
// Before

// After

