import type {User} from 'types/User';

export type Message = {
  id: string,
  text: string,
  sender: User,
  date: Date,
  readers: User[],
}

export type Conversation = {
  id: string,
  participants: User[],
  messages: Message[],
}