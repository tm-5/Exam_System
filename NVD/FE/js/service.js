const getMethods = async (url) => {
    let str = url.split('?');
    let authId = getUserId();
    if(str.length > 1) url = url + '&authId=' + authId + str[1];
    else url = url + '?authId=' + authId;

    const response = await fetch(
        url,
        {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': '*',
                'Access-Control-Allow-Headers': '*'
            }
        }
    );
    return await response.json();
}

const postMethods = async (url, data) => {
    let str = url.split('?');
    let authId = getUserId();
    if(str.length > 1) url = url + '&authId=' + authId + str[1];
    else url = url + '?authId=' + authId;

    if(data == null){
        const response = await fetch(
            url,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                }
            }
        );
        return await response.json();
    }
    else{
        const response = await fetch(
            url,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                },
                body: JSON.stringify(data)
            }
        );
        return await response.json();
    }
}

const putMethods = async (url, data) => {
    let str = url.split('?');
    let authId = getUserId();
    if(str.length > 1) url = url + '&authId=' + authId + str[1];
    else url = url + '?authId=' + authId;

    if(data == null){
        const response = await fetch(
            url,
            {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                }
            }
        );
        return await response.json();
    }
    else{
        const response = await fetch(
            url,
            {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                },
                body: JSON.stringify(data)
            }
        );
        return await response.json();
    }
}

const deleteMethods = async (url) => {
    let str = url.split('?');
    let authId = getUserId();
    if(str.length > 1) url = url + '&authId=' + authId + str[1];
    else url = url + '?authId=' + authId;

    const response = await fetch(
        url,
        {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }
    );
    return await response.json();
}