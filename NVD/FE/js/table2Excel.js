let excelFormat = {
    "Directory": {
        "workbooks": [
            "/xl/workbook.xml"
        ],
        "sheets": [
            "/xl/worksheets/sheet1.xml"
        ],
        "charts": [],
        "dialogs": [],
        "macros": [],
        "rels": [],
        "strs": [
            "/xl/sharedStrings.xml"
        ],
        "comments": [],
        "links": [],
        "coreprops": [
            "/docProps/core.xml"
        ],
        "extprops": [
            "/docProps/app.xml"
        ],
        "custprops": [
            "/docProps/custom.xml"
        ],
        "themes": [
            "/xl/theme/theme1.xml"
        ],
        "styles": [
            "/xl/styles.xml"
        ],
        "vba": [],
        "drawings": [],
        "TODO": [
            "/customXml/itemProps1.xml",
            "/customXml/itemProps2.xml",
            "/customXml/itemProps3.xml"
        ],
        "xmlns": "http://schemas.openxmlformats.org/package/2006/content-types",
        "calcchain": "",
        "sst": "/xl/sharedStrings.xml",
        "style": "/xl/styles.xml",
        "defaults": {
            "bin": "application/vnd.openxmlformats-officedocument.spreadsheetml.printerSettings",
            "rels": "application/vnd.openxmlformats-package.relationships+xml",
            "xml": "application/xml"
        }
    },
    "Workbook": {
        "AppVersion": {
            "appName": "xl",
            "appname": "xl",
            "lastEdited": "7",
            "lastedited": "7",
            "lowestEdited": "7",
            "lowestedited": "7",
            "rupBuild": "27328",
            "rupbuild": "27328"
        },
        "WBProps": {
            "defaultThemeVersion": 166925,
            "allowRefreshQuery": false,
            "autoCompressPictures": true,
            "backupFile": false,
            "checkCompatibility": false,
            "CodeName": "",
            "date1904": false,
            "filterPrivacy": false,
            "hidePivotFieldList": false,
            "promptedSolutions": false,
            "publishItems": false,
            "refreshAllConnections": false,
            "saveExternalLinkValues": true,
            "showBorderUnselectedTables": true,
            "showInkAnnotation": true,
            "showObjects": "all",
            "showPivotChartFilter": false,
            "updateLinks": "userSet"
        },
        "WBView": [
            {
                "xWindow": "468",
                "xwindow": "468",
                "yWindow": "3048",
                "ywindow": "3048",
                "windowWidth": "17280",
                "windowwidth": "17280",
                "windowHeight": "8964",
                "windowheight": "8964",
                "uid": "{8A614F3E-7A97-4296-8344-96E627DDCD0C}",
                "activeTab": 0,
                "autoFilterDateGrouping": true,
                "firstSheet": 0,
                "minimized": false,
                "showHorizontalScroll": true,
                "showSheetTabs": true,
                "showVerticalScroll": true,
                "tabRatio": 600,
                "visibility": "visible"
            }
        ],
        "Sheets": [
            {
                "name": "Sheet1",
                "sheetId": "1",
                "sheetid": "1",
                "id": "rId1",
                "Hidden": 0
            }
        ],
        "CalcPr": {
            "calcId": "191029",
            "calcid": "191029",
            "calcCompleted": "true",
            "calcMode": "auto",
            "calcOnSave": "true",
            "concurrentCalc": "true",
            "fullCalcOnLoad": "false",
            "fullPrecision": "true",
            "iterate": "false",
            "iterateCount": "100",
            "iterateDelta": "0.001",
            "refMode": "A1"
        },
        "Names": [],
        "xmlns": "http://schemas.openxmlformats.org/spreadsheetml/2006/main",
        "Views": [
            {}
        ]
    },
    "Props": {
        "LastAuthor": "Đỗ Dũng",
        "Author": "Đỗ Dũng",
        "CreatedDate": "2024-03-03T13:03:26.000Z",
        "ModifiedDate": "2024-03-28T12:01:48.000Z",
        "Application": "Microsoft Excel",
        "AppVersion": "16.0300",
        "DocSecurity": "0",
        "HyperlinksChanged": false,
        "SharedDoc": false,
        "LinksUpToDate": false,
        "ScaleCrop": false,
        "Worksheets": 1,
        "SheetNames": [
            "Sheet1"
        ]
    },
    "Custprops": {
        "ContentTypeId": "0x010100F5891EA5B856DE4798CC28C89672975B"
    },
    "Deps": {},
    "Sheets": {
        
    },
    "SheetNames": [
        "Sheet1"
    ]
}

const saveExcel = () => {
    excelFormat.Sheets["Sheet1"] = getTable();
    XLSX.writeFile(excelFormat, 'ThongKe.xlsx');
}

const getTable = () => {
    let totalCol = 0;
    let totalRow = 0;
    let table = document.getElementById("products-datatable");

    let row = table.getElementsByTagName("tr");
    totalRow = row.length;
    let tbRow = {};
    for(let i = 0; i < row.length; i++){
        let cell = row[i].getElementsByTagName("td");
        if(i==0) cell = row[i].getElementsByTagName("th");
        if(cell.length > totalCol) totalCol = cell.length;

        for(let j = 0; j < cell.length; j++){
            tbRow[String(intToChar(j+1)+(i+1))] = format(cell[j].innerText);
        }
    }

    tbRow["!ref"] = "A1:"+intToChar(totalCol)+totalRow;
    tbRow["!margins"] = {left: 0.7, right: 0.7, top: 0.75, bottom: 0.75, header: 0.3, footer: 0.3};
    return tbRow;
}

const format = (value) => {
    if(isNaN(value)) return {t: "s", v: value, r: "<t>"+value+"</t>", h: value, w: value};
    return {t: "n", v: Number(value), w: value};
}

const intToChar = (i) => {
    let rs = '';
    while(i > 0){
        let c = i%26;
        if(c == 0) c = 26;
        rs = String.fromCharCode(64 + c) + rs;
        i = Math.floor((i-1)/26);
    }
    return rs;
}