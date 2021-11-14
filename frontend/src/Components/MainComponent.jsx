import React, { useEffect, useState } from 'react';
import axios from 'axios';
import UrlItem from './UrlItems';

const MainComponent = () => {

    const [url, setUrl] = useState();
    const [urls, setUrls] = useState([]);
    const [shortUrl, setShortUrl] = useState(null);

    //using useEffect hook to get data from server
    useEffect(() => {
        axios.get('http://localhost:8080/urls').then(response => {
            console.log(response.data);
            setUrls(response.data);
        })
    }, [shortUrl]);

    //defining onChangeHandler function
    const onChangeHandler = (e) => {
        setUrl(e.target.value);
        console.log(url);
    }

    //defining onSubmitHandler function
    const onSubmitHandler = (e) => {
        e.preventDefault();

        let formData = {
            "longUrl": url
        }

        axios.post('http://localhost:8080/create', formData, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            console.log(response.data);
            setShortUrl(`${response.data}`);
        })
    }

    return (
        <>

            {/* The Main Form */}
            <form onSubmit={onSubmitHandler}>
                <input required type="URL" onChange={onChangeHandler} placeholder="Enter your url here" name="" id="" />
                <button>Shorten</button>
            </form>

            {/* Shortend Url */}
            {
                shortUrl ?
                    <>
                        <div className="surl-container">
                            <span style={{ fontSize: '.8rem', marginTop: '0rem', marginBottom: '.5rem', color: '#5A2989', textTransform: 'uppercase' }}>Shortened Url</span>
                            <a href={shortUrl} rel="noreferrer" target="_blank" style={{ fontSize: '1.5rem' }} className="surl"> {shortUrl} </a>
                        </div>
                    </>
                    : null
            }


            {/* Recent Shortner Urls */}
            <div className="rsu">
                <span style={{ marginBottom: '2rem', fontSize: '1.2rem' }}>Url Shortener - Recently Shortened Urls</span>
                <table>
                    <tr>
                        <th>
                            Created Date
                        </th>
                        <th>
                            Long Url
                        </th>
                        <th>
                            Short Url
                        </th>
                        <th>Hits</th>
                    </tr>
                {
                    
                    urls.map(theUrl => {
                        return <UrlItem url={theUrl} />
                    })
                }
                </table>

            </div>

            <footer>
         
            </footer>
        </>
    );
}

export default MainComponent;