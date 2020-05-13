export const listItemTemplate = value =>
    `<div class="list-item border border-gray-200 py-2 px-4 text-gray-800">
    ${value}
    <button class="hover:bg-gray-300 hover:text-gray-500 text-gray-300 px-1 rounded-full float-right">
       <span class="mdi mdi-delete"></span>
    </button>
  </div>`;

export const searchItemTemplate = station =>
    `<span class="mdi mdi-arrow-right-bold text-gray-500"></span>
        <span class="text-gray-600">${station.name}</span>`

export const searchResultTemplate = result => {
    const stationTemplate = result.stations.slice(1,-1)
        .map(station => searchItemTemplate(station))
        .join("");

    const length = result.stations.length;
    return `<button
            id="favorite-button"
            class="favorite-button bg-yellow-500 mdi mdi-star-outline absolute text-white bg-transparent d-inline-block hover:bg-yellow-500 font-semibold hover:text-white py-2 px-3 hover:border-transparent rounded-full z-10"
    >
    </button>
    <ul class="flex border-b w-full">
      <li class="-mb-px w-2/4">
        <a class="w-full text-center bg-white inline-block border-l border-t border-r py-2 px-4 text-gray-700 font-semibold" href="#">최단거리</a>
      </li>
      <li class="w-2/4">
        <a class="w-full bg-gray-200 text-center bg-white inline-block py-2 px-4 text-gray-500 hover:text-gray-700 font-semibold" href="#"
        >최소시간</a
        >
      </li>
    </ul>
    <div class="px-2 py-4 border-b">
      <div class="w-full flex mb-3">
        <div class="inline-block w-1/2 border-r text-center">
          <div class="text-gray-600 text-sm">소요시간</div>
          <div>${result.duration}</div>
        </div>
        <div class="inline-block w-1/2 text-center">
          <div class="text-gray-600 text-sm">거리</div>
          <div>${result.distance}</div>
        </div>
      </div>
    </div>
    <div class="relative pt-3 pb-10">
      <div class="px-2 py-1 w-full flex">
        <div class="w-10/12 inline-block">
          <span class="font-bold">
        
            ${result.stations[0].name}
       
          </span>
          ${stationTemplate}
          <span class="font-bold">
        
            ${result.stations[length-1].name}
       
          </span>
<!--          <span class="mdi mdi-arrow-right-bold text-gray-500"></span>-->
<!--          <span class="text-gray-600">역삼</span>-->
<!--          <span class="mdi mdi-arrow-right-bold text-gray-500"></span>-->
<!--          <span class="text-gray-600">선릉</span>-->
<!--          <span class="mdi mdi-arrow-right-bold text-gray-500"></span>-->
<!--          <span class="text-gray-600">삼성</span>-->
<!--          <span class="mdi mdi-arrow-right-bold text-gray-500"></span>-->
<!--          <span class="font-bold">-->
<!--       -->
<!--          </span>-->
        </div>
      </div>
    </div>`;
}

export const subwayLinesTemplate = line =>
    `<div class="subway-line-item border border-gray-200 py-2 px-4 text-gray-800">
      <span class="${line.bgColor} w-3 h-3 rounded-full inline-block mr-1"></span>
      ${line.title}
      <button class="hover:bg-gray-300 hover:text-gray-500 text-gray-300 px-1 rounded-full float-right">
         <span class="mdi mdi-delete"></span>
      </button>
      <button class="hover:bg-gray-300 hover:text-gray-500 text-gray-300 px-1 rounded-full float-right">
         <span class="mdi mdi-pencil"></span>
      </button>
    </div>`;

export const optionTemplate = value => `<option>${value}</option>`;

const navTemplate = `<nav class="flex items-center justify-between flex-wrap bg-yellow-500 p-4">
  <div class="flex items-center flex-shrink-0 text-gray-800 w-full">
      <a href="/" class="mr-2">
        <img src="/service/images/logo_small.png" class="w-6">
      </a>
    <div class="flex justify-start">
      <div class="hover:bg-yellow-400 px-2 py-1 rounded">
         <a href="/stations" class="block inline-block lg:mt-0 text-gray-800 text-sm">
          역 관리
          </a>
      </div>
      <div class="hover:bg-yellow-400 px-2 py-1 rounded">
         <a href="/lines" class="block inline-block lg:mt-0 text-gray-800 text-sm">
          노선 관리
          </a>
      </div>
      <div class="hover:bg-yellow-400 px-2 py-1 rounded">
          <a href="/edges" class="block inline-block lg:mt-0 text-gray-800 text-sm">
          구간 관리
          </a>
      </div>
    </div>
</nav>`;

export const subwayLinesItemTemplate = line => {
    const stationsTemplate = line.stations
        .map(station => listItemTemplate(station))
        .join("");
    return `<div class="inline-block w-1/2 px-2">
            <div class="rounded-sm w-full slider-list">
              <div class="border ${line.bgColor} lint-title px-4 py-1">${line.title}</div>
              <div class="overflow-y-auto height-90">
              ${stationsTemplate}
              </div>
            </div>
          </div>`;
};

export const initNavigation = () => {
    document.querySelector("body").insertAdjacentHTML("afterBegin", navTemplate);
};

export const colorSelectOptionTemplate = (option, index) => {
    const hasNewLine = ++index % 7 === 0;

    return ` <button data-color="${
        option.bgColor
    }" class="color-select-option button w-6 h-6 ${option.bgColor} ${
        option.hoverColor
    } font-bold p-1 rounded">
             </button> ${hasNewLine ? "<br/>" : ""}`;
};
