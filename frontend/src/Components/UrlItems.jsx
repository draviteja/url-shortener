import React from 'react';
import moment from 'moment';

const UrlItem = ({ url }) => {
    return (
        <>

           
                    <tr>
                        <td>
                            <p style={{ flex: '0 0 15%', fontSize: '.8rem' }}> {moment(`${url.createdAt}`).format('MMMM D, YY - LT')} </p>
                        </td>
                        <td>
                        <a href={`${url.longUrl}`} rel="noreferrer" target="_blank" style={{ flex: '0 0 35%' }}> {`${url.longUrl}`}  </a>
                        </td>
                        <td>
                            <a href={`${url.shortUrl}`} rel="noreferrer" target="_blank" style={{ flex: '0 0 35%' }}> {`${url.shortUrl}`}  </a>
                        </td>
                        <td>
                            {`${url.hits}`}
                        </td>
                    </tr>
    
               

        </>
    );
}

export default UrlItem;