////////////////////////////////// 1 //////////////////////////////////
async function _initOverview(): Promise<void> {
  // ...
  const { results: productTypes }: PaginatedResponse<ProductTypeOverview> = await this._getProductTypes();

  // Если нет активных продуктов, то проверить количество неактивных для отображения в шаблоне
  if (!productTypes.length && this.publishedFilter === PUBLISH_STATUSES.PUBLISHED_ONLY) {
    const { count }: PaginatedResponse<ProductTypeOverview> = await this._getProductTypes({ published: 'false' });
    this.unpublishedQuantity = count;
  }
  this.products = productTypes;
}

////////////////////////////////// 2 //////////////////////////////////
function ngAfterContentChecked() {
  // Коррестировка activeId, если activeId был неправильно инициализирован
  const activeTab = _getTabById(this.activeId);
  this.activeId = activeTab ? activeTab.id : (this.tabs.length ? this.tabs.first.id : null);
}

function _getTabById(id: string): TabItem {
  const tabsWithId: TabItem = this.tabs.find(tab => tab.id === id);
  return tabsWithId || null;
}

////////////////////////////////// 3 //////////////////////////////////
function tap(bakery: Bakery) {
  // ...
  // отчистка фильтров
  this.publishedFilter = PUBLISH_STATUSES.PUBLISHED_ONLY;
  this.queryFilter = '';

  // При смене пекарни, необходимо отписаться от всех событый, чтобы
  // избежать повторного вызова обработчиков событий
  this.bakeryRelatedSubscriptions.forEach((s) => {
    s.unsubscribe();
  });
  this.bakeryRelatedSubscriptions = [];

  // ...
}


////////////////////////////////// 4 //////////////////////////////////
@HostListener('window:beforeinstallprompt', ['$event'])
onbeforeinstallprompt(e);
{
  // Предотвратить появление модального окна для Chrome 67 и более ранних
  e.preventDefault();
  // Спрятать событие beforeinstallprompt для отложенного вызова
  this._swUpdateService.deferredPrompt = e;
}


////////////////////////////////// 5 //////////////////////////////////
this._userService.user$
  // ...
  .subscribe((user) => {
    const gtag = (window as any).gtag;
    // Супер админ не должен генерировать событие для аналитики "page_view"
    if (this._userService.isSuperAdmin()) {
      gtag('configure', this.ga4AnalyticKey, { 'send_page_view': false });
    } else {
      gtag('configure', this.ga4AnalyticKey, { 'send_page_view': true });
    }
  });


////////////////////////////////// 6 //////////////////////////////////
try {
  this._processToken();
} catch (err) {
  if (!(err instanceof CieAuthTokenNotFoundError)) {
    // токен получен с нарушениями, необходима дополнительная проверка
    throw err;
  }
}

////////////////////////////////// 7 //////////////////////////////////
// Случай, когда формат инвойса не действует в выбранной стране
if (!this.availableFormats.find((f) => f.format === this.invoiceFormat)) {
  this.invoiceFormat = this.availableFormats.length === 1
    ? this.availableFormats[0].format
    : null;
}



