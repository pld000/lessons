////////////////////////////////// 1 //////////////////////////////////
/**
 * Связывание проиходит на этапе сборки приложения, значения хранятся
 * в конфикурационных файлах, и в зависимости от окружения, в котором
 * происходит сборка (development или production), значения могут быть разными
 * */
function initIntervalCheckers() {
  const { unconfirmedOrdersInterval, beepSignInterval } = environment;

  console.log('INIT intervals', 'unconfirmedOrdersModalInterval', unconfirmedOrdersInterval, 'beepSignInterval', beepSignInterval);
  // ...
}

const { analytic } = environment;
const analyticKeys = {
  gaKeys: [analytic.ga, analytic.ga4Keys, analytic.googleAnalyticKey]
    .filter(key => Boolean(key)),
  gtmKey: analytic.gtm
};

////////////////////////////////// 2 //////////////////////////////////
/**
 * Связывание через констануту. Базовые настройки текстового редактора, который
 * используется в разных частях приложения. При необходимости, легко внести
 * изменения в одном конкретном месте кода, или расширить дополнительными
 * настройками в конкретном месте связывания
 * */
export const CK_EDITOR_CONFIG = {
  toolbar: [
    'bold', 'italic', '|', 'bulletedList', '|', 'numberedList', '|', 'link', '|', 'undo', 'redo'
  ],
  link: {
    addTargetToExternalLinks: true
  }
};

const config = { ...CK_EDITOR_CONFIG, startupFocus: true };

////////////////////////////////// 3 //////////////////////////////////
/**
 * Связывание во время выполнения программы. Здесь значение переменной areas,
 * запрашивается из базы данных, и используется для построения критериев поиска
 * и следующих запросов к базе данных.
 * */
async function _init() {
  // ...
  const areas = await this._locationApi.getAllAreas();
  const criteria = this._searchCriteriaService
    .prepareCriteria(searchCriteria, areas);
  // ...
}

////////////////////////////////// 4 //////////////////////////////////
/**
 * Значние присваивается во время написания кода. Значение переменной imageMbSizeLimit
 * не используется за пределами конкретной области видимости, не зависит
 * от рабочего окружения (development или production), поэтому чтобы не
 * усложнять код и не добавлять лишней зависимости в виде импортируемой
 * константы, лучше сделать прямое присвоение.
 * */

function imageFileProcess(file) {
  if (!file) {
    return false;
  }

  const imageMbSizeLimit = 21348301;
  if (file && file.size > imageMbSizeLimit) {
    this.fileValidationError = this._translate.instant('cakeBuilder.cakeMotiveSection.uploadImage.fileSizeLimitError');
    this._resetDropZone();
    return this._analyticGa.trackImageSizeOverflow(30);
  }

  return this.fileChosen(file);
}
