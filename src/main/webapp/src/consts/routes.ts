/* eslint max-len: 0 */
export const LOGIN_ROUTE = '/login';
export const CONVERSATIONS_ROUTE = '/conversations';

export const LOGIN_API = '/api/user/login';
export const REGISTER_API = '/api/user/register';
export const USER_LIST_API = '/api/user/list';
export const CONVERSATIONS_API = '/api/conversations';
export const MESSAGE_API = (id: string) => `${CONVERSATIONS_API}/${id}`;
